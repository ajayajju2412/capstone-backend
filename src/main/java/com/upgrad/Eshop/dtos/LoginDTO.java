package com.upgrad.Eshop.dtos;

import lombok.Data;

@Data
public class LoginDTO {
    private String userName;
    private String password;
    private String message;
    private String jwtToken;
}

