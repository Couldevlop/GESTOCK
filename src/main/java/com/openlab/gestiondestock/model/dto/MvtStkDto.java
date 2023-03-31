package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.enums.TypeMvtStk;
import com.openlab.gestiondestock.model.AbstractEntity;
import com.openlab.gestiondestock.model.MvtStk;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto extends AbstractEntity {
    private Integer id;

    private ArticleDto article;

    private Instant dateMvt;

    private BigDecimal quantite;

    private Integer idEntreprise;

    private TypeMvtStk typeMvtStk;

    public static MvtStkDto fromEntity(MvtStk mvtStk){
        if(mvtStk == null){
            return null;
        }
        return MvtStkDto.builder()
                .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                .dateMvt(mvtStk.getDateMvt())
                .typeMvtStk(mvtStk.getTypeMvtStk())
                .idEntreprise(mvtStk.getIdEntreprise())
                .quantite(mvtStk.getQuantite())
                .id(mvtStk.getId()).build();
    }
    public static MvtStk fromEntityDTO(MvtStkDto mvtStkDto){
        if(mvtStkDto == null){
            return null;
        }
        MvtStk mvtStk =  new MvtStk();
        mvtStk.setTypeMvtStk(mvtStkDto.typeMvtStk);
        mvtStk.setDateMvt(mvtStkDto.dateMvt);
        mvtStk.setArticle(ArticleDto.fromEntityDTO(mvtStkDto.article));
        mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        return mvtStk;
    }
}
