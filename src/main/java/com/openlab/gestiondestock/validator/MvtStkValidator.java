package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {

    public  static List<String> validate(MvtStkDto mvtStkDto){
        List<String> errors = new ArrayList<>();

        if(mvtStkDto == null){
            errors.add("Veillez renseigner la quantité du mouvement de stock");
            errors.add("Veillez renseigner le type de mouvement de stock");
            errors.add("Veillez renseigner la quantité du mouvement");
            errors.add("Veillez renseigner la date du mouvement");
            errors.add("Veillez renseigner le type de mouvement");
            return errors;
        }
        if(mvtStkDto.getQuantite() == null){
            errors.add("Veillez renseigner la quantité du mouvement");
        }
        if(mvtStkDto.getTypeMvtStk().name() == null){
            errors.add("Veillez renseigner le type de mouvement");
        }
        if(mvtStkDto.getDateMvt() == null){
            errors.add("Veillez renseigner la date du mouvement");
        }

        if(StringUtils.hasLength(mvtStkDto.getTypeMvtStk().name())){
            errors.add("Veillez renseigner le type de mouvement");
        }

        if(mvtStkDto.getArticle() == null){
            errors.add("Aucun article n'a été electionné pour ce mouvent de stock");
        }



        return errors;
    }
}
