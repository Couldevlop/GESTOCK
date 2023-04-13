package com.openlab.gestiondestock.validator;

import com.openlab.gestiondestock.model.dto.AdresseDto;
import com.openlab.gestiondestock.model.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {
    public static List<String> validate(AdresseDto adresseDto) {
        List<String> errors = new ArrayList<>();
        if (adresseDto == null) {
            errors.add("veillez renseigner le code l'adresse");
            errors.add("veillez renseigner le code postal");
            errors.add("veillez renseigner le pays");
            errors.add("veillez renseigner la ville");
            errors.add("veillez renseigner l'adresse 1");
        }
        if (!StringUtils.hasLength(adresseDto.getAdresse1())) {
            errors.add("veillez renseigner l'adresse 1");
        }
        if (!StringUtils.hasLength(adresseDto.getVille())) {
            errors.add("veillez renseigner la ville");
        }
        if (!StringUtils.hasLength(adresseDto.getPays())) {
            errors.add("veillez renseigner le pays");
        }
        if (!StringUtils.hasLength(adresseDto.getCodePostale())) {
            errors.add("veillez renseigner le code postal");
        }

        return errors;
    }
}
