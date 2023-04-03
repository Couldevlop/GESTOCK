package com.openlab.gestiondestock.security.controller;

import com.openlab.gestiondestock.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/syperadmin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final UtilisateurRepository utilisateurRepository;

    @GetMapping("/hi")
    public String sayHi ()
    {
        return "Hi" ;
    }
}
