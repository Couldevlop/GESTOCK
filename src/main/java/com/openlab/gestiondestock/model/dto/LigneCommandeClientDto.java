package com.openlab.gestiondestock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.CommandeClient;
import com.openlab.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeClientDto {
    private Integer id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private  BigDecimal prixUnitaire;

    private Integer idEntreprise;


    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient){
        if(ligneCommandeClient == null){
            return null;
        }
        return  LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .quantite(ligneCommandeClient.getQuantite())
                .idEntreprise(ligneCommandeClient.getIdEntreprise())
                .build();
    }


    public static LigneCommandeClient fromEntityDTO(LigneCommandeClientDto dto){
        if(dto == null){
            return null;
        }
        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(dto.getId());
        ligneCommandeClient.setArticle(ArticleDto.fromEntityDTO(dto.article));
        ligneCommandeClient.setQuantite(dto.getQuantite());
        ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
        ligneCommandeClient.setPrixUnitaire(dto.prixUnitaire);
        return ligneCommandeClient;
    }
}
