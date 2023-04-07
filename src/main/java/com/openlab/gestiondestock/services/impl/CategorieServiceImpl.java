package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.dto.CategorieDto;
import com.openlab.gestiondestock.repository.CategorieRepository;
import com.openlab.gestiondestock.services.CategorieService;
import com.openlab.gestiondestock.validator.CategorieValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    @Override
    public CategorieDto save(CategorieDto categorieDto) {
        /*
        *Verification de la validité de l'objet CATEGORIE
         */

        List<String> errors = CategorieValidator.validate(categorieDto);
        if(!errors.isEmpty()){
           log.error("l'objet est invalide {}", categorieDto);
           throw new InvalidEntityException("catégorie invalide", ErrorCodes.CATEGORIE_NOT_VALID, errors);
        }
        return CategorieDto.fromEntity(categorieRepository.save(CategorieDto.fromEntityDto(categorieDto)));
    }

    @Override
    public CategorieDto findById(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return null;
        }

        return categorieRepository.findById(id).map(CategorieDto::fromEntity).orElseThrow(()->{
            throw new EntityNotFoundException("Categoirie avec l'id " + id + " est introuvable", ErrorCodes.CATEGORIE_NOT_FOUND);
        });
    }

    @Override
    public CategorieDto findByCode(String code) {
        if(code ==  null){
            log.error("le code fourni est null");
            return null;
        }
        return categorieRepository.findByCode(code).map(CategorieDto::fromEntity).orElseThrow(()->{
                    throw new EntityNotFoundException("La catégorie avec le code" + code + "est introuvable",ErrorCodes.CATEGORIE_NOT_FOUND);
                }
        );
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll().stream().map(CategorieDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return;
        }
        categorieRepository.deleteById(id);
    }
}
