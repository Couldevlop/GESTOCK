package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.FournisseurDto;


import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto fournisseurDto);
    FournisseurDto findById(Integer id);
    FournisseurDto findByCode(String code);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}
