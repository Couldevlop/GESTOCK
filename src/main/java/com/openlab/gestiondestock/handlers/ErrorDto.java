package com.openlab.gestiondestock.handlers;

import com.openlab.gestiondestock.exceptions.ErrorCodes;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Builder
@Data
public class ErrorDto {
    private Integer httpCode;
    private ErrorCodes code;
    private String message;
    private List<String> errors = new ArrayList<>();
}
