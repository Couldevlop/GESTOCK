package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto utilisateurDto);
    UtilisateurDto findById(Integer id);
    UtilisateurDto findByCode(String code);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
}
