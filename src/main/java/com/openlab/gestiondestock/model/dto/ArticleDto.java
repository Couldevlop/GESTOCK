package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {
    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private Integer idEntreprise;
    private CategorieDto categorie;

    public static ArticleDto fromEntity(Article article){
        if(article == null){
            return  null;
        }
        return  ArticleDto.builder()
                .id(article.getId())
                .designation(article.getDesignation())
                .codeArticle(article.getCodeArticle())
                .photo(article.getPhoto())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .tauxTva(article.getTauxTva())
                .idEntreprise(article.getIdEntreprise())
                .categorie(CategorieDto.fromEntity(article.getCategorie())).build();

    }

    public static Article fromEntityDTO(ArticleDto articleDto){
        Article article = new Article();
        article.setCodeArticle(articleDto.codeArticle);
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(article.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleDto.prixUnitaireTtc);
        article.setPhoto(articleDto.photo);
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setCategorie(articleDto.categorie.fromEntityDto(articleDto.getCategorie()));
        return article;
    }
}
