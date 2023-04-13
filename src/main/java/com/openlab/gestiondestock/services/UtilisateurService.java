package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.ChangerMotDePasseUtilisateurDto;
import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto utilisateurDto);
    UtilisateurDto findById(Integer id);
    UtilisateurDto findByUsername(String username);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
    UtilisateurDto changerMotDePasseUtilisateurDto(ChangerMotDePasseUtilisateurDto dto);
}
