package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.CommandeFournisseur;
import com.openlab.gestiondestock.model.dto.FournisseurDto;
import com.openlab.gestiondestock.repository.CommandeFournisseurRepository;
import com.openlab.gestiondestock.repository.FournisseurRepository;
import com.openlab.gestiondestock.services.FournisseurService;
import com.openlab.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final CommandeFournisseurRepository commandeFournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if(!errors.isEmpty()){
            log.error("objet fourni est invalide");
            throw new InvalidEntityException("Objet n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.fromEntityDTO(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if(id == null){
            log.error("l'id fourni est null");
        }
        return fournisseurRepository.findById(id).map(FournisseurDto::fromEntity).orElseThrow(()->{
            throw new EntityNotFoundException("fournisseur introuvable", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        });
    }


    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findByAllFournisseurId(id);
        if(!commandeFournisseurs.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer le fournisseur car il est associé à des commandes", ErrorCodes.FOURNISSEUR_ALREADY_IN_USED);
        }
    }
}
