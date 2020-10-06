package com.upgrad.Eshop.exceptions;

public class UserRoleDetailsNotFoundException extends Exception{
    public UserRoleDetailsNotFoundException() {
        super();
    }

    public UserRoleDetailsNotFoundException(String message) {
        super(message);
    }

    public UserRoleDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserRoleDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
