package com.openlab.gestiondestock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlab.gestiondestock.model.Adresse;
import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Data
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String photo;

    private AdresseDto adresse;

    private String email;

    private String numTel;

    private Integer idEntreprise;

    @JsonIgnore
    private List<CommandeClientDto> commandeClient;



    public static ClientDto fromEntity(Client client){
        if(client == null){
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .photo(client.getPhoto())
                .adresse(AdresseDto.fromEntity(client.getAdresse()))
                .numTel(client.getNumTel())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .idEntreprise(client.getIdEntreprise())
                .build();
    }
     public static Client fromEntityDTO(ClientDto clientDto){
        if(clientDto == null){
            return  null;
        }

                Client client = new Client();
                client.setId(clientDto.id);
                client.setPhoto(clientDto.getPhoto());
                client.setEmail(clientDto.getEmail());
                client.setNom(clientDto.getNom());
                client.setNumTel(clientDto.getNumTel());
                client.setIdEntreprise(client.getIdEntreprise());
                client.setPrenom(clientDto.getPrenom());
                client.setAdresse(AdresseDto.fromEntityDTO(clientDto.getAdresse()));
                return client;
     }

}
