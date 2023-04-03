package com.openlab.gestiondestock.security.controller;

import com.openlab.gestiondestock.repository.UtilisateurRepository;
import com.openlab.gestiondestock.security.controller.api.UserApi;
import com.openlab.gestiondestock.security.dto.LoginDto;
import com.openlab.gestiondestock.security.dto.RegisterDto;
import com.openlab.gestiondestock.security.service.IUtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final IUtilisateurService iUtilisateurService;


    @Override
    public ResponseEntity<String> authenticate(LoginDto loginDto) {
        return  ResponseEntity.ok(iUtilisateurService.authenticate(loginDto));
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        return ResponseEntity.ok(iUtilisateurService.register(registerDto));
    }
}
