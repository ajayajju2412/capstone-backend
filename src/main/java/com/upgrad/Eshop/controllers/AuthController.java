package com.upgrad.Eshop.controllers;

import com.upgrad.Eshop.dtos.LoginDTO;
import com.upgrad.Eshop.dtos.UserDTO;
import com.upgrad.Eshop.entities.Users;
import com.upgrad.Eshop.exceptions.*;
import com.upgrad.Eshop.security.jwt.JwtTokenProvider;
import com.upgrad.Eshop.services.UserService;
import com.upgrad.Eshop.utils.DTOEntityConverter;
import com.upgrad.Eshop.utils.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    /*@Autowired
    CustomerValidator customerValidator;*/
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping(method = RequestMethod.POST, value = "/auth/register")
    @ResponseBody
    public ResponseEntity signUp(@RequestBody UserDTO userDTO)
            throws CustomException, UserRoleDetailsNotFoundException, DuplicateEmailException, UsernameExistsException {
        System.out.println("entered sign up in AuthController");

        //customerValidator.validateCustomer(customerDTO);
        try {
            //Users user = userService.getUserByUsername(userDTO.getUserName());
            if (userService.getUserByUsername(userDTO.getUserName()) != null) {

                /*throw new UsernameExistsException(
                        "Username already exists : " + userDTO.getUserName());*/
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Try any other userName, this userName is already registered!");
            }
            if (userService.getUserByEmail(userDTO.getEmail()) != null) {

                    /*throw new UsernameExistsException(
                            "Username already exists : " + userDTO.getUserName());*/
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Try any other email address, this email address is already registered!");
            }
        }catch (UserDetailsNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Map<String, String> model = new HashMap<>();
            String username = userDTO.getUserName();
            String password = userDTO.getPassword();

            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                model.put("Error", "Username is invalid/ Password is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
            }

            String newToken = jwtTokenProvider.createToken(username);
            //converting DTO to entity
            Users newUser = dtoEntityConverter.convertToUserEntity(userDTO);
            //saving the new user
            newUser.setRole("USER");
            newUser.setCreated(new Date(System.currentTimeMillis()));
            newUser.setUpdated(new Date(System.currentTimeMillis()));
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
            Users savedUser = userService.saveUser(newUser);
            //converting entity back to DTO
            UserDTO savedUserDTO = entityDTOConverter.convertToUserDTO(savedUser);
            //setting token to DTO
            savedUserDTO.setJwtToken(newToken);
            //response
            System.out.println("Successful signup");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
        } catch (Exception e) {
            throw new CustomException("Username " + userDTO.getUserName() + " already registered",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginDTO loginDTO)
            throws CustomException, InvalidCredentialsException, UserDetailsNotFoundException, DuplicateEmailException, UsernameExistsException {
        //System.out.println("Print statement here _____________________________");
        //customerValidator.validateuserLogin(loginDTO);
        System.out.println("entered login in AuthController");
        Map<String, String> model = new HashMap<>();
        String username = loginDTO.getUserName();
        String password = loginDTO.getPassword();
        if (StringUtils.isEmpty(username) || userService.getUserByUsername(username) == null) {
            //model.put("Error", "Username is invalid/ Password is empty");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This userName has not been registered!");
        }
        Users user = userService.getUserByUsername(username);
        user.setUpdated(new Date(System.currentTimeMillis()));
        Users savedUser = userService.saveUser(user);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String encodedPassword = bCryptPasswordEncoder.encode(password);
        //System.out.println(encodedPassword);
        if (StringUtils.isEmpty(password) || !encoder.matches(password, savedUser.getPassword())) {
            //throw new InvalidCredentialsException("Invalid Credentials!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials!");
        }
        String token = jwtTokenProvider.createToken(username);
        //UserDTO savedUserDTO = entityDTOConverter.convertToUserDTO(savedUser);
        //savedUserDTO.setJwtToken(token);
        LoginDTO loginDTO1 = new LoginDTO();
        loginDTO1.setUserName(loginDTO.getUserName());
        loginDTO1.setMessage("Success");
        loginDTO1.setJwtToken(token);
        System.out.println("Successful login");
        return ResponseEntity.status(HttpStatus.OK).body(loginDTO1);
    }


}

