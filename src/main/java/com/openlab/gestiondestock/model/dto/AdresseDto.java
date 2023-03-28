package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Adresse;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Builder
@Data
public class AdresseDto {
    private String adresse1;

    private String adresse2;

    private String ville;

    private String codePostale;

    private String pays;

    public static AdresseDto fromEntity(Adresse adresse){
        if(adresse == null){
            return null;
        }
        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .codePostale(adresse.getCodePostale())
                .pays(adresse.getPays())
                .ville(adresse.getVille())
                .build();
    }


    public static Adresse fromEntityDTO(AdresseDto adresseDto){
        if(adresseDto ==  null){
            return null;
        }
        return Adresse.builder()
                .adresse1(adresseDto.getAdresse1())
                .adresse2(adresseDto.getAdresse2())
                .codePostale(adresseDto.getCodePostale())
                .pays(adresseDto.getPays())
                .ville(adresseDto.getVille())
                .build();
    }
}
