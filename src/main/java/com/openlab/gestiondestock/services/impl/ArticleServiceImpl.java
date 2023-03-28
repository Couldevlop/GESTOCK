package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.dto.ArticleDto;
import com.openlab.gestiondestock.repository.ArticleRepository;
import com.openlab.gestiondestock.services.ArticleService;
import com.openlab.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ArticleServiceImpl1")
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(errors.isEmpty()){
            log.error("l'article est invalide {}", articleDto);
            throw new InvalidEntityException("l'article est invalide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.fromEntityDTO(articleDto))) ;
    }

    @Override
    public ArticleDto findById(Integer id) {
        if(id == null){
            log.error("Aucun article avec l'id" + id + "est introuvable dans la BDD");
            throw new EntityNotFoundException("article introuvable", ErrorCodes.ARTICLE_NOT_FOUND);
        }
        Optional<Article>  article = articleRepository.findById(id);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->{
            throw new EntityNotFoundException("Aucun article avec l'id" + id + "est introuvable dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND);});
    }

    @Override
    public ArticleDto findByCodeArticle(String code) {
        if(StringUtils.hasLength(code)){
            log.error("Aucun article avec le code" + code + "est introuvable dans la BDD");
            throw new EntityNotFoundException("article introuvable", ErrorCodes.ARTICLE_NOT_FOUND);
        }
        Optional<Article>  article = articleRepository.findArticleByCodeArticle(code);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->{
            throw new EntityNotFoundException("Aucun article avec le code" + code + "est introuvable dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND);});
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Aucun article avec l'id" + id + "est introuvable dans la BDD");
            throw new EntityNotFoundException("article introuvable", ErrorCodes.ARTICLE_NOT_FOUND);
        }
        articleRepository.deleteById(id);
    }
}