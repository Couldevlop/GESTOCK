package com.openlab.gestiondestock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.Categorie;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Data
public class CategorieDto {
    private Integer id;

    private String code;

    private Integer idEntreprise;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> articles;

    public static CategorieDto fromEntity(Categorie categorie){
        if(categorie == null){
            return null;
        }
        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .idEntreprise(categorie.getIdEntreprise())
                .designation(categorie.getDesignation()).build();
    }

    public static Categorie fromEntityDto(CategorieDto categorieDto){
        if(categorieDto == null){
            return  null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setCode(categorieDto.getCode());
        categorie.setIdEntreprise(categorieDto.getIdEntreprise());
        categorie.setDesignation(categorieDto.getDesignation());
        return  categorie;

    }
}
