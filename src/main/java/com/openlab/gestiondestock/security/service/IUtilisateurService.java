package com.openlab.gestiondestock.security.service;

import com.openlab.gestiondestock.model.Roles;
import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.security.dto.LoginDto;
import com.openlab.gestiondestock.security.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface IUtilisateurService {
    String authenticate(LoginDto loginDto);
    ResponseEntity<?> register (RegisterDto registerDto);
    Roles saveRole(Roles role);

    Utilisateur saverUser (Utilisateur user) ;
}
