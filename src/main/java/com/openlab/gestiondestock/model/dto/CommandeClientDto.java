package com.openlab.gestiondestock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.model.CommandeClient;
import com.openlab.gestiondestock.model.LigneCommandeClient;
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
public class CommandeClientDto {
    private Integer id;

    private String code;

    private Instant dateCommande;

    private ClientDto client;

    private Integer idEntreprise;

    private List<LigneCommandeClientDto> ligneCommandeClient;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if(commandeClient == null){
            return null;
        }

        return  CommandeClientDto.builder()
                .id(commandeClient.getId())
                .idEntreprise(commandeClient.getIdEntreprise())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .build();
    }

     public static CommandeClient fromEntityDTO(CommandeClientDto dto){
        if(dto == null){
            return  null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(dto.getId());
        commandeClient.setCode(dto.getCode());
        commandeClient.setDateCommande(dto.getDateCommande());
        commandeClient.setClient(ClientDto.fromEntityDTO(dto.getClient()));
        return commandeClient;
     }
}
