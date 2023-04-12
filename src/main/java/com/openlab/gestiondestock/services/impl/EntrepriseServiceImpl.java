package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.controller.api.EntrepriseApi;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.dto.EntrepriseDto;
import com.openlab.gestiondestock.repository.EntrepriseRepository;
import com.openlab.gestiondestock.services.EntrepriseService;
import com.openlab.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    final private EntrepriseRepository entrepriseRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }


    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if(!errors.isEmpty()){
            log.error("l'objet fourni est invalid");
            throw new InvalidEntityException("l'objet est invalide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.fromEntityDTO(entrepriseDto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return null;
    }

    @Override
    public EntrepriseDto findByEmail(String email) {
        return null;
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
