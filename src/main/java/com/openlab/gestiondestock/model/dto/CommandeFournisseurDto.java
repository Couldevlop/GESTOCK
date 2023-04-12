package com.openlab.gestiondestock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.CommandeFournisseur;
import com.openlab.gestiondestock.model.Fournisseur;
import com.openlab.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeFournisseurDto {
    private Integer id;

    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private Integer idEntreprise;

    private EtatCommande etatCommande;

    @JsonIgnore
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseur;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){
        if(commandeFournisseur == null){
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .idEntreprise(commandeFournisseur.getIdEntreprise()).build();
    }

    public static CommandeFournisseur fromEntityDTO(CommandeFournisseurDto dto){
        if(dto == null){
            return null;
        }

        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setCode(dto.getCode());
        commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        commandeFournisseur.setDateCommande(dto.getDateCommande());
        commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        commandeFournisseur.setEtatCommande(dto.getEtatCommande());
        commandeFournisseur.setId(dto.getId());
        return commandeFournisseur;
    }

    public boolean isCommandeLivree(){
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }


}
