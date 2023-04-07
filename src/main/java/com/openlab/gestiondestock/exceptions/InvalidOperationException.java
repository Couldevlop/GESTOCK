package com.openlab.gestiondestock.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalidOperationException extends RuntimeException {
    @Getter
    private ErrorCodes errorCodes;
    @Getter
    List<String> errors;

    public InvalidOperationException(String message){
        super(message);
    }

    public InvalidOperationException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidOperationException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes=errorCodes;
    }

    public InvalidOperationException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }

    public InvalidOperationException(String message, ErrorCodes errorCodes, List<String> errors){
        super(message);
        this.errorCodes = errorCodes;
        this.errors = errors;
    }
}
