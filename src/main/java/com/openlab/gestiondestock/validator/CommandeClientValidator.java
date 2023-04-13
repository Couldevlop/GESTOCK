package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {
    public static List<String> validate(CommandeClientDto commandeClientDto){
        List<String> errors = new ArrayList<>();


        if(commandeClientDto == null){
            errors.add("Veillez renseigner le nom de l'utilisateur");
            errors.add("Veillez renseigner le prenom de l'utilisateur");
            errors.add("Veillez renseigner le nom de l'entreprise de l'utilisateur");
            errors.add("Veillez renseigner l'adresse mail de l'utilisateur");
            errors.add("Veillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veillez renseigner le numéro de téléphone de l'utilisateur");
            errors.add("Veillez renseigner une adresse");
            return errors;
        }

        if(commandeClientDto == null || commandeClientDto.getDateCommande() == null){
            errors.add("Veillez renseignerle champs 'Date de Commande'  de la commande client");
        }
        if(commandeClientDto == null || StringUtils.hasLength(commandeClientDto.getCode())){
            errors.add("Veillez renseigner le champs 'code' de la commande du client");
        }
        if(commandeClientDto == null || StringUtils.hasLength(commandeClientDto.getEtatCommande().toString())){
            errors.add("Veillez renseigner le champs l'etat de la commande du client");
        }
        if(commandeClientDto.getClient() == null){

            errors.add("Veillez renseigner le nom de l'utilisateur");
            errors.add("Veillez renseigner le prenom de l'utilisateur");
            errors.add("Veillez renseigner le nom de l'entreprise de l'utilisateur");
            errors.add("Veillez renseigner l'adresse mail de l'utilisateur");
            errors.add("Veillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veillez renseigner une adresse");
        }else {
            if(commandeClientDto.getClient() == null || StringUtils.hasLength(commandeClientDto.getClient().getNom())){
                errors.add("Veillez renseigner le nom du client de l'utilisateur");
            }
            if(commandeClientDto.getClient() == null || StringUtils.hasLength(commandeClientDto.getClient().getPrenom())){
                errors.add("Veillez renseigner le prenom du client");
            }
            if(commandeClientDto.getClient() == null || StringUtils.hasLength(commandeClientDto.getClient().getEmail())){
                errors.add("Veillez renseigner l'email du client ");
            }
            if(commandeClientDto.getClient() == null || StringUtils.hasLength(commandeClientDto.getClient().getNumTel())){
                errors.add("Veillez renseigner le numéro de téléphone du client");
            }
            if(commandeClientDto.getClient().getAdresse() == null){
                errors.add("Veillez renseigner une adresse");
            }
        }
        return errors;
    }
}
