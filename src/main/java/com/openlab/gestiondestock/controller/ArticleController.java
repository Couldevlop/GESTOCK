package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.services.ArticleService;
import com.openlab.gestiondestock.services.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    @Qualifier("ArticleServiceImpl1")
    private final ArticleServiceImpl articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }
}
