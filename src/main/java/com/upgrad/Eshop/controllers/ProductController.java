package com.upgrad.Eshop.controllers;

import com.upgrad.Eshop.dtos.ProductDTO;
import com.upgrad.Eshop.entities.Product;
import com.upgrad.Eshop.entities.Users;
import com.upgrad.Eshop.exceptions.ProductNotFoundException;
import com.upgrad.Eshop.exceptions.UserDetailsNotFoundException;
import com.upgrad.Eshop.exceptions.UserRoleDetailsNotFoundException;
import com.upgrad.Eshop.security.jwt.JwtTokenProvider;
import com.upgrad.Eshop.services.ProductService;
import com.upgrad.Eshop.services.UserService;
import com.upgrad.Eshop.utils.DTOEntityConverter;
import com.upgrad.Eshop.utils.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    //work2
    @PostMapping(value="/products")
    public ResponseEntity addProduct(@RequestBody ProductDTO productDTO, @RequestHeader(value = "X-ACCESS-TOKEN") String jwtToken) throws UserRoleDetailsNotFoundException, UserDetailsNotFoundException {
        System.out.println("entered addProduct");
        String username = jwtTokenProvider.getUsername(jwtToken);

        if(userService.getUserByUsername(username) == null){
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please Login first to access this endpoint!");
        }
        if(!userService.getUserByUsername(username).getRole().equalsIgnoreCase("ADMIN")){
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to access this endpoint!");
        }

        Product newProduct = dtoEntityConverter.convertToProductEntity(productDTO);
        newProduct.setCreated(new Date(System.currentTimeMillis()));
        newProduct.setUpdated(new Date(System.currentTimeMillis()));
        Product savedProduct = productService.saveProduct(newProduct);

        ProductDTO savedProductDTO = entityDTOConverter.convertToProductDTO(savedProduct);
        System.out.println("addProduct successful");
        return ResponseEntity.status(HttpStatus.MULTI_STATUS.OK).body(savedProductDTO);
    }

    //work1
    @GetMapping(value="/products/{id}")
    public ResponseEntity fetchProduct(@PathVariable int id) throws ProductNotFoundException {
        System.out.println("entered fetchProduct in ProductController");
        Product responseProduct = productService.getProduct(id);
        if(responseProduct == null){
            //return ResponseEntity.status(HttpStatus.).body(user);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
            System.out.println("No product");
        }

        ProductDTO responseProductDTO = entityDTOConverter.convertToProductDTO(responseProduct);
        //logger.debug("Get movie details :" + responseMovieDTO);
        System.out.println("successfull product fetch");
        return ResponseEntity.status(HttpStatus.OK).body(responseProduct);
        //return productService.getProduct(id).orElse(null);
    }

    @PutMapping("/products/{id}")
    public Product modifyProduct(@PathVariable int id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/products/{id}")
    public String removeProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
}
