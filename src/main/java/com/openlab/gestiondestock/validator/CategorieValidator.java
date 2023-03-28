package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.CategorieDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {

    public static List<String> validate(CategorieDto categorieDto){
        List<String> errors = new ArrayList<>();
        if(categorieDto == null || !StringUtils.hasLength(categorieDto.getCode())){
            errors.add("veillez renseigner le code de la catégorie");
        }
        return errors;
    }
}
