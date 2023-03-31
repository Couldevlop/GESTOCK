package com.openlab.gestiondestock.security.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
