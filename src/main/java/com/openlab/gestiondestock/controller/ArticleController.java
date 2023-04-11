package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.ArticleApi;
import com.openlab.gestiondestock.model.dto.ArticleDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;
import com.openlab.gestiondestock.model.dto.LigneVenteDto;
import com.openlab.gestiondestock.services.ArticleService;
import com.openlab.gestiondestock.services.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {
    @Qualifier("ArticleServiceImpl1")
    private final ArticleService articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String code) {
        return articleService.findByCodeArticle(code);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVente(Integer idArticle) {
        return articleService.findHistoriqueVente(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return articleService.findHistoriqueCommandeClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandeFournisseur(idArticle);
    }

    @Override
    public List<ArticleDto> findAllArticleByCategorie(Integer idCategorie) {
        return articleService.findAllArticleByCategorie(idCategorie);
    }
}
