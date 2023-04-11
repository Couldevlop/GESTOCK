package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.dto.ArticleDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;
import com.openlab.gestiondestock.model.dto.LigneVenteDto;
import com.openlab.gestiondestock.repository.*;
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
    private final LigneVenteRepository ligneVenteRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, VenteRepository venteRepository,
                              LigneVenteRepository ligneVenteRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                              LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()){
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

    @Override
    public List<LigneVenteDto> findHistoriqueVente(Integer idArticle) {
        return ligneVenteRepository.findAllByArticleId(idArticle).stream()
                .map(LigneVenteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return ligneCommandeClientRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return ligneCommandeFournisseurRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticleByCategorie(Integer idCategorie) {
        return articleRepository.findAllByCategorieId(idCategorie).stream()
                .map(ArticleDto::fromEntity).collect(Collectors.toList());
    }
}
