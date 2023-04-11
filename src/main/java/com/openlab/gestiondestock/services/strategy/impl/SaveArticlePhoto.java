package com.openlab.gestiondestock.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.dto.ArticleDto;
import com.openlab.gestiondestock.services.ArticleService;
import com.openlab.gestiondestock.services.FlickrService;
import com.openlab.gestiondestock.services.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("articleStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveArticlePhoto implements Strategy<ArticleDto> {

    private final FlickrService flickrService;
    private final ArticleService articleService;


    @Override()
    public ArticleDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        ArticleDto article = articleService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo,titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        article.setPhoto(urlPhoto);
        return articleService.save(article);
    }
}
