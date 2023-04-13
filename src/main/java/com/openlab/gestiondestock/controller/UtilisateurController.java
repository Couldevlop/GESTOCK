package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.UtilisateursApi;
import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import com.openlab.gestiondestock.services.UtilisateurService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UtilisateurController implements UtilisateursApi {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return utilisateurService.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByCode(String code) {
        return utilisateurService.findByUsername(code);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
      utilisateurService.delete(id);
    }
}
