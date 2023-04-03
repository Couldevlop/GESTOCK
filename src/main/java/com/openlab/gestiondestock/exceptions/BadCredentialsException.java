package com.openlab.gestiondestock.exceptions;

import lombok.Getter;

public class BadCredentialsException extends RuntimeException{
    @Getter
    private ErrorCodes errorCodes;

    public BadCredentialsException(String message){
        super(message);
    }

    public BadCredentialsException(String message, Throwable cause){
        super(message, cause);
    }

    public BadCredentialsException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes=errorCodes;
    }

    public BadCredentialsException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }
}
