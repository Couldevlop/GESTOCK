package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.FournisseurApi;
import com.openlab.gestiondestock.model.dto.FournisseurDto;
import com.openlab.gestiondestock.services.FournisseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDto> save(FournisseurDto fournisseurDto) {
        return null;
    }

    @Override
    public ResponseEntity<FournisseurDto> findById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<FournisseurDto> findByCode(String code) {
        return null;
    }

    @Override
    public ResponseEntity<List<FournisseurDto>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity delete(Integer id) {
        return null;
    }
}
