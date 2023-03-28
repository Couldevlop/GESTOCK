package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Adresse;
import com.openlab.gestiondestock.model.Entreprise;
import com.openlab.gestiondestock.model.Roles;
import com.openlab.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class UtilisateurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private Instant DateDeNaissance;

    private String photo;

    private String email;

    private String motDePasse;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur == null){
            return null;
        }
        return   UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .motDePasse(utilisateur.getMotDePasse())
                .DateDeNaissance(utilisateur.getDateDeNaissance())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .build();
    }


    public static Utilisateur fromEntityDTO(UtilisateurDto utilisateurDto){
        if(utilisateurDto == null){
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setAdresse(AdresseDto.fromEntityDTO(utilisateurDto.getAdresse()));
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setEntreprise(EntrepriseDto.fromEntityDTO(utilisateurDto.getEntreprise()));
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        return  utilisateur;
    }
}
