package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {
    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto){
        List<String> errors = new ArrayList<>();


        if(commandeFournisseurDto == null){
            errors.add("Veillez renseignerle champs 'Date de Commande'  de la commande fournisseur");
            errors.add("Veillez renseigner le champs 'code' de la commande du fournisseur");
            return errors;
        }

        if(commandeFournisseurDto == null || commandeFournisseurDto.getDateCommande() == null){
            errors.add("Veillez renseignerle champs 'Date de Commande'  de la commande fournisseur");
        }
        if(commandeFournisseurDto == null || StringUtils.hasLength(commandeFournisseurDto.getCode())){
            errors.add("Veillez renseigner le champs 'code' de la commande du fournisseur");
        }
        if(commandeFournisseurDto.getFournisseur() == null){

            errors.add("Veillez renseigner le nom du fournisseur");
            errors.add("Veillez renseigner le prenom du fournisseur");
            errors.add("Veillez renseigner le nom du fournisseur");
            errors.add("Veillez renseigner du fournisseur");
            errors.add("Veillez renseigner le mot de passe du fournisseur");
            errors.add("Veillez renseigner une adresse");
        }else {
            if(commandeFournisseurDto.getFournisseur() == null || StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getNom())){
                errors.add("Veillez renseigner le nom  du fournisseur");
            }
            if(commandeFournisseurDto.getFournisseur() == null || StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getPrenom())){
                errors.add("Veillez renseigner le prenom du fournisseur");
            }
            if(commandeFournisseurDto.getFournisseur() == null || StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getEmail())){
                errors.add("Veillez renseigner l'email du fournisseur ");
            }
            if(commandeFournisseurDto.getFournisseur() == null || StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getNumTel())){
                errors.add("Veillez renseigner le numéro de téléphone du fournisseur");
            }
            if(commandeFournisseurDto.getFournisseur().getAdresse() == null){
                errors.add("Veillez renseigner une adresse");
            }else {
                if(StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getAdresse().getAdresse1())){
                    errors.add("le champs 'adresse1'  est obigatoire");
                }
                if(StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getAdresse().getPays())){
                    errors.add("le champs 'pays'  est obigatoire");
                }
                if(StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getAdresse().getVille())){
                    errors.add("le champs 'ville'  est obigatoire");
                }
                if(StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getAdresse().getCodePostale())){
                    errors.add("le champs 'code postale'  est obigatoire");
                }
            }
        }

        return errors;
    }
}
