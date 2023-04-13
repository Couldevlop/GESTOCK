package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {
    public static List<String> validate(VentesDto ventesDto){
        List<String> errors = new ArrayList<>();
        if(ventesDto == null || !StringUtils.hasLength(ventesDto.getCode())){
            errors.add("veillez renseigner la vente");
        }
        return errors;
    }
}
