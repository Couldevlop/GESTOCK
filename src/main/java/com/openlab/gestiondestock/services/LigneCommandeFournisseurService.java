package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.LigneCommandeFournisseur;
import com.openlab.gestiondestock.model.dto.ArticleDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;

import java.util.List;

public interface LigneCommandeFournisseurService {
    LigneCommandeFournisseurDto save(ArticleDto articleDto);
    LigneCommandeFournisseurDto findById(Integer id);
    LigneCommandeFournisseurDto findByCodeArticle(String code);
    List<LigneCommandeFournisseurDto> findAll();
    void delete(Integer id);
}
