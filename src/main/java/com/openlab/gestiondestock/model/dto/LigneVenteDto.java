package com.openlab.gestiondestock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlab.gestiondestock.model.LigneVente;
import com.openlab.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Builder
@Data
public class LigneVenteDto {
    private Integer id;

    @JsonIgnore
    private VentesDto ventes;

    private BigDecimal quantite;

    private  BigDecimal prixUnitaire;

    private ArticleDto article;

    private Integer idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if(ligneVente == null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .quantite(ligneVente.getQuantite()).build();
    }

    public static LigneVente fromEntity(LigneVenteDto ligneVenteDto){
        if(ligneVenteDto == null){
            return null;
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setIdEntreprise(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setArticle(ArticleDto.fromEntityDTO(ligneVenteDto.getArticle()));
        ligneVente.setPrixUnitaire(ligneVenteDto.prixUnitaire);
        return ligneVente;
    }

}
