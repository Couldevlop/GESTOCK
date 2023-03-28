package com.openlab.gestiondestock.model.dto;

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

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseur;

    private static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){
        if(commandeFournisseur == null){
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
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
        commandeFournisseur.setId(dto.getId());
        return commandeFournisseur;
    }
}
