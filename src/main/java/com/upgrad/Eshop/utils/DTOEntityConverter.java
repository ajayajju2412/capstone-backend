package com.upgrad.Eshop.utils;

import com.upgrad.Eshop.dtos.ProductDTO;
import com.upgrad.Eshop.dtos.UserDTO;
import com.upgrad.Eshop.entities.Product;
import com.upgrad.Eshop.entities.Users;
import com.upgrad.Eshop.exceptions.UserRoleDetailsNotFoundException;
//import com.upgrad.Eshop.services.UserRoleService;
import org.springframework.stereotype.Component;

@Component
public class DTOEntityConverter {
    /*@Autowired
    UserRoleService userRoleService;*/

    public Users convertToUserEntity(UserDTO userDTO) throws UserRoleDetailsNotFoundException {
        Users users = new Users();

        users.setFirstName(userDTO.getFirstName());
        users.setLastName(userDTO.getLastName());
        users.setPhoneNumber(userDTO.getPhoneNumber());
        users.setEmail(userDTO.getEmail());
        users.setUsername(userDTO.getUserName());
        users.setPassword(userDTO.getPassword());

        //to be removed later
        users.setCreated(userDTO.getCreated());
        users.setUpdated(userDTO.getUpdated());
        users.setRole(userDTO.getRole());

        //users.setRole(userRoleService.getUserRoleDetails(userDTO.getUserRoleID()));

        return users;
    }

    public Product convertToProductEntity(ProductDTO productDTO) throws UserRoleDetailsNotFoundException {
        Product product = new Product();

        product.setAvailableItems(productDTO.getAvailableItems());
        product.setCategory(productDTO.getCategory());
        product.setCreated(productDTO.getCreated());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setManufacturer(productDTO.getManufacturer());
        product.setName(productDTO.getName());
        product.setUpdated(productDTO.getUpdated());
        product.setPrice(productDTO.getPrice());

        //to be removed later



        return product;
    }
}
