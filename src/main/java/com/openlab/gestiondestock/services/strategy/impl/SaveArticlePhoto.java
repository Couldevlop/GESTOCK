package com.openlab.gestiondestock.services.strategy.impl;

import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.services.ArticleService;
import com.openlab.gestiondestock.services.FlickrService;
import com.openlab.gestiondestock.services.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveArticlePhoto implements Strategy<Article> {

    private final FlickrService flickrService;
    private final ArticleService articleService;


    @Override
    public Article savePhoto(Integer id,InputStream photo, String titre) {
        return null;
    }
}
