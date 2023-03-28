package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.VentesDto;

import java.util.List;

public interface VenteService {
    VentesDto save(VentesDto ventesDto);
    VentesDto findById(Integer id);
    VentesDto findByCode(String code);
    List<VentesDto> findAll();
    void delete(Integer id);
}
