package com.openlab.gestiondestock.security.dto;

import com.openlab.gestiondestock.model.Roles;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto {
    String nom ;
    String prenom ;
    String email;
    String password ;

}
