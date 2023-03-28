package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFournisseurDto {
    private Integer id;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;

    private  BigDecimal prixUnitaire;
}
