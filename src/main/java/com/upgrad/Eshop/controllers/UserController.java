package com.upgrad.Eshop.controllers;

import com.upgrad.Eshop.entities.Users;
import com.upgrad.Eshop.exceptions.APIException;
import com.upgrad.Eshop.exceptions.DuplicateEmailException;
import com.upgrad.Eshop.exceptions.UserDetailsNotFoundException;
import com.upgrad.Eshop.exceptions.UsernameExistsException;
import com.upgrad.Eshop.security.jwt.JwtTokenProvider;
import com.upgrad.Eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping(value="/users/details")
    public ResponseEntity fetchUserDetails(@RequestHeader(value = "X-ACCESS-TOKEN") String jwtToken) throws APIException, UserDetailsNotFoundException {
        ResponseEntity responseEntity = null;
        String username = jwtTokenProvider.getUsername(jwtToken);
        if (username == null) {
            //throw new APIException("Please Login first to access this endpoint!");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please Login first to access this endpoint!");
        }
        Users user = userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}