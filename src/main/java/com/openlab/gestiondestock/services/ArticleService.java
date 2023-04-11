package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.ArticleDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;
import com.openlab.gestiondestock.model.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto articleDto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String code);
    List<ArticleDto> findAll();
    void delete(Integer id);
    List<LigneVenteDto> findHistoriqueVente(Integer idArticle);
    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);
    List<ArticleDto> findAllArticleByCategorie(Integer idCategorie);


}
