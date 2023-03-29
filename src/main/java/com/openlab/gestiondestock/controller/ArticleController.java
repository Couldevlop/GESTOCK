package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.ArticleApi;
import com.openlab.gestiondestock.model.dto.ArticleDto;
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
}
