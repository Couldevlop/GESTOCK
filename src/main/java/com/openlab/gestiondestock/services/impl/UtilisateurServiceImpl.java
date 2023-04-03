package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.model.Utilisateur;
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
        Utilisateur user = utilisateurRepository.save(UtilisateurDto.fromEntityDTO(utilisateurDto));
        return UtilisateurDto.fromEntity(user);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurRepository.findById(id).map(UtilisateurDto::fromEntity).orElseThrow(()->{
            throw new EntityNotFoundException("Utilisateur introuvable");
        });

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
