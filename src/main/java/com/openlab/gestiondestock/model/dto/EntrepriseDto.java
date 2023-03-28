package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Adresse;
import com.openlab.gestiondestock.model.Entreprise;
import com.openlab.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class EntrepriseDto {
    private Integer id;

    private String nom;

    private String description;

    private AdresseDto adresse;

    private String numTel;

    private String codeFiscal;

    private String email;

    private String photo;

    private String siteWeb;

    private List<UtilisateurDto> utilisateur;


    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if(entreprise == null){
            return null;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
                .photo(entreprise.getPhoto())
                .codeFiscal(entreprise.getCodeFiscal())
                .description(entreprise.getDescription())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .siteWeb(entreprise.getSiteWeb())
                .build();
    }


    public static Entreprise fromEntityDTO(EntrepriseDto entrepriseDto){
        if(entrepriseDto == null){
            return  null;
        }

        Entreprise entreprise = new Entreprise();
            entreprise.setAdresse(AdresseDto.fromEntityDTO(entrepriseDto.getAdresse()));
            entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
            entreprise.setEmail(entrepriseDto.getEmail());
            entreprise.setDescription(entrepriseDto.getDescription());
            entreprise.setNom(entrepriseDto.getNom());
            entreprise.setNumTel(entrepriseDto.getNumTel());
            entreprise.setPhoto(entrepriseDto.getPhoto());
            entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
            return entreprise;
    }
}
