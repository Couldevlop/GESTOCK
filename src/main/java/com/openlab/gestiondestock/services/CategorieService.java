package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.CategorieDto;


import java.util.List;

public interface CategorieService {
    CategorieDto save(CategorieDto categorieDto);
    CategorieDto findById(Integer id);
    CategorieDto findByCode(String code);
    List<CategorieDto> findAll();
    void delete(Integer id);
}
