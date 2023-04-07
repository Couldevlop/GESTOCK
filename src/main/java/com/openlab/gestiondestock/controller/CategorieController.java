package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.CategorieApi;
import com.openlab.gestiondestock.model.dto.CategorieDto;
import com.openlab.gestiondestock.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CategorieController implements CategorieApi {
    @Qualifier("CategorieServiceImpl")
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }


    @Override
    public CategorieDto save(CategorieDto categorieDto) {
        return categorieService.save(categorieDto);
    }

    @Override
    public CategorieDto findById(Integer id) {
        return categorieService.findById(id);
    }

    @Override
    public CategorieDto findByCode(String code) {
        return categorieService.findByCode(code);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Integer id) {
       categorieService.delete(id);
    }
}
