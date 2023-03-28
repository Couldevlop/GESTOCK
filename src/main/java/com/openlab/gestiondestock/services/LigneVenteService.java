package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.LigneVenteDto;

import java.util.List;

public interface LigneVenteService {
    LigneVenteDto save(LigneVenteDto ligneVenteDto);
    LigneVenteDto findById(Integer id);
    LigneVenteDto findByCode(String code);
    List<LigneVenteDto> findAll();
    void delete(Integer id);
}
