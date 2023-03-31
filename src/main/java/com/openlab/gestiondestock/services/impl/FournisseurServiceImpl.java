package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.model.dto.FournisseurDto;
import com.openlab.gestiondestock.repository.FournisseurRepository;
import com.openlab.gestiondestock.services.FournisseurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        return null;
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return null;
    }

    @Override
    public FournisseurDto findByCode(String code) {
        return null;
    }

    @Override
    public List<FournisseurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
