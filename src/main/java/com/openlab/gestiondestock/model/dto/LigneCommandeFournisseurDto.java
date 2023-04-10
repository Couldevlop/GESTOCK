package com.openlab.gestiondestock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlab.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFournisseurDto {
    private Integer id;

    private ArticleDto article;
    @JsonIgnore
    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;

    private  BigDecimal prixUnitaire;

    private Integer idEntreprise;


    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur){
        if(ligneCommandeFournisseur == null){
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise()).build();
    }

    public static  LigneCommandeFournisseur fromEntityDTO(LigneCommandeFournisseurDto dto){
        if (dto == null){
            return null;
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(dto.getId());
        ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        ligneCommandeFournisseur.setQuantite(dto.getQuantite());
        ligneCommandeFournisseur.setPrixUnitaire(dto.prixUnitaire);
        ligneCommandeFournisseur.setArticle(ArticleDto.fromEntityDTO(dto.getArticle()));
        return ligneCommandeFournisseur;
    }

}
