package com.openlab.gestiondestock.security.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto {
    String firstName ;
    String lastName ;
    String email;
    String password ;
}
