package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import com.openlab.gestiondestock.model.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto entrepriseDto);
    EntrepriseDto findById(Integer id);
    EntrepriseDto findByEmail(String email);
    List<EntrepriseDto> findAll();
    void delete(Integer id);
}
