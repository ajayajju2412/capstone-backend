package com.upgrad.Eshop.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException(String e) {
                super(e);
    }


}
