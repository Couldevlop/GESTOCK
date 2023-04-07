package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.controller.api.EntrepriseApi;
import com.openlab.gestiondestock.model.dto.EntrepriseDto;
import com.openlab.gestiondestock.repository.EntrepriseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseApi {
    final private EntrepriseRepository entrepriseRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }


    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        return null;
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return null;
    }

    @Override
    public EntrepriseDto findByNom(String nom) {
        return null;
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
