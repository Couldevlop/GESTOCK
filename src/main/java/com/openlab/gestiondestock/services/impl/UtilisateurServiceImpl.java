package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import com.openlab.gestiondestock.repository.UtilisateurRepository;
import com.openlab.gestiondestock.services.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return null;
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return null;
    }

    @Override
    public UtilisateurDto findByCode(String code) {
        return null;
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
