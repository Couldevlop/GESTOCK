package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Adresse;
import com.openlab.gestiondestock.model.CommandeFournisseur;
import com.openlab.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class FournisseurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String photo;

    private AdresseDto adresse;

    private String email;

    private String numTel;

    private Integer idEntreprise;

    private List<CommandeFournisseurDto> commandeFournisseur;


    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur == null){
            return null;
        }
        return FournisseurDto.builder()
                .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .email(fournisseur.getEmail())
                .id(fournisseur.getId())
                .idEntreprise(fournisseur.getIdEntreprise())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .numTel(fournisseur.getNumTel())
                .photo(fournisseur.getPhoto()).build();
    }

    public static Fournisseur fromEntityDTO(FournisseurDto dto){
        if(dto == null){
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setAdresse(AdresseDto.fromEntityDTO(dto.getAdresse()));
        fournisseur.setEmail(dto.getEmail());
        fournisseur.setNom(dto.getNom());
        fournisseur.setPrenom(dto.getPrenom());
        fournisseur.setNumTel(dto.getNumTel());
        fournisseur.setPhoto(dto.getPhoto());
        fournisseur.setIdEntreprise(dto.getIdEntreprise());
        fournisseur.setId(dto.getId());
        return fournisseur;
    }
}
