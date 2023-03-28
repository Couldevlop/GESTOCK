package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors = new ArrayList<>();

        if(utilisateurDto == null){
            errors.add("Veillez renseigner le nom de l'utilisateur");
            errors.add("Veillez renseigner le prenom de l'utilisateur");
            errors.add("Veillez renseigner le nom de l'entreprise de l'utilisateur");
            errors.add("Veillez renseigner l'adresse mail de l'utilisateur");
            errors.add("Veillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veillez renseigner une adresse");
            return errors;
        }

        if(utilisateurDto == null || StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veillez renseigner le nom de l'utilisateur");
        }
        if(utilisateurDto == null || StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veillez renseigner le prenom de l'utilisateur");
        }
        if(utilisateurDto == null || StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("Veillez renseigner l'adresse mail de l'utilisateur");
        }
        if(utilisateurDto == null || StringUtils.hasLength(utilisateurDto.getEntreprise().getNom())){
            errors.add("Veillez renseigner le nom de l'entreprise de l'utilisateur");
        }
        if(utilisateurDto == null || StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("Veillez renseigner le mot de passe de l'utilisateur");
        }
        if(utilisateurDto.getAdresse() == null ){
            errors.add("Veillez renseigner une adresse");
        }else{
            if(StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())){
                errors.add("le champs 'adresse1'  est obigatoire");
            }
            if(StringUtils.hasLength(utilisateurDto.getAdresse().getPays())){
                errors.add("le champs 'pays'  est obigatoire");
            }
            if(StringUtils.hasLength(utilisateurDto.getAdresse().getVille())){
                errors.add("le champs 'ville'  est obigatoire");
            }
            if(StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())){
                errors.add("le champs 'code postale'  est obigatoire");
            }

        }
        return errors;
    }
}
