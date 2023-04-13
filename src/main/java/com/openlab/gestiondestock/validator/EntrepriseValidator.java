package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.EntrepriseDto;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto entrepriseDto){
        List<String> errors = new ArrayList<>();
        if(entrepriseDto == null){

            errors.add("Veillez renseigner le nom de l'entreprise ");
            errors.add("Veillez renseigner l'adresse mail de l'entreprise");
            errors.add("Veillez renseigner une adresse");
        }
        errors.addAll(AdresseValidator.validate(entrepriseDto.getAdresse()));
        return errors;
    }
}
