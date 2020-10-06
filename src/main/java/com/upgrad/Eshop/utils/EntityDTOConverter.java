package com.upgrad.Eshop.utils;

import com.upgrad.Eshop.dtos.ProductDTO;
import com.upgrad.Eshop.dtos.UserDTO;
import com.upgrad.Eshop.entities.Product;
import com.upgrad.Eshop.entities.Users;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOConverter {
   /* @Autowired
    UserService userService;*/

    public UserDTO convertToUserDTO(Users users){
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(users.getFirstName());
        userDTO.setLastName(users.getLastName());
        userDTO.setPhoneNumber(users.getPhoneNumber());
        userDTO.setEmail(users.getEmail());
        userDTO.setUserName(users.getUsername());
        userDTO.setPassword(users.getPassword());

        //to be removed later
        userDTO.setCreated(users.getCreated());
        userDTO.setUpdated(users.getUpdated());
        userDTO.setRole(users.getRole());

        return userDTO;
    }

    public ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setAvailableItems(product.getAvailableItems());
        productDTO.setCategory(product.getCategory());
        productDTO.setCreated(product.getCreated());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setManufacturer(product.getManufacturer());
        productDTO.setName(product.getName());
        productDTO.setUpdated(product.getUpdated());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }


}
