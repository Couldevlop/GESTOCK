package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto clientDto){
        List<String> errors = new ArrayList<>();
        if (clientDto == null) {

            errors.add("Veillez renseigner le clien");
            errors.add("veillez renseigner le nom du client");
            errors.add("veillez renseigné le prenom du client ");
            errors.add("veillez renseigner le numero de telephone");
        }
        if(!StringUtils.hasLength(clientDto.getNumTel())){
            errors.add("veillez renseigner le numero de télephone du client");
        }
        if(!StringUtils.hasLength(clientDto.getNom())){
            errors.add("veillez renseigner le nom du client");
        }
        if(!StringUtils.hasLength(clientDto.getPrenom())){
            errors.add("veillez renseigner le prenom du client");
        }

        errors.addAll(AdresseValidator.validate(clientDto.getAdresse()));
        return errors;
    }
}
