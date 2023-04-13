package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.model.dto.ChangerMotDePasseUtilisateurDto;
import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import com.openlab.gestiondestock.repository.UtilisateurRepository;
import com.openlab.gestiondestock.services.UtilisateurService;
import com.openlab.gestiondestock.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UserValidator.validate(utilisateurDto);
        if(!errors.isEmpty()){
            log.error("L'objet est invalide");
            throw new InvalidEntityException("", ErrorCodes.UTILSATEUR_NOT_VALID, errors);
        }
        if(Boolean.TRUE.equals(utilisateurRepository.existsByEmail(utilisateurDto.getEmail()))){
            log.error("Un autre utilisateur a déjà ce mail");
            throw  new InvalidEntityException("L'eamil est déjà utilisé", ErrorCodes.UTILSATEUR_NOT_VALID);
        }

        Utilisateur user = utilisateurRepository.save(UtilisateurDto.fromEntityDTO(utilisateurDto));
        return UtilisateurDto.fromEntity(user);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if(id == null){
            log.error("l'id fourni est null");
            throw new EntityNotFoundException("Impossible de trouver un utilisateur avec un id null", ErrorCodes.UTILSATEUR_NOT_FOUND);
        }
        return utilisateurRepository.findById(id).map(UtilisateurDto::fromEntity).orElseThrow(()->{
            throw new EntityNotFoundException("Utilisateur introuvable", ErrorCodes.UTILSATEUR_NOT_FOUND);
        });

    }

    @Override
    public UtilisateurDto findByUsername(String username) {
        if(username == null){
            log.error("username fourni est vide");
            throw new EntityNotFoundException("Impossible de trouver un utilisateur avec un username vide", ErrorCodes.UTILSATEUR_NOT_FOUND);
        }
        return utilisateurRepository.findByUsername(username).map(UtilisateurDto::fromEntity).orElseThrow(()->{
            throw new EntityNotFoundException("Aucun utilisateur ne correspond à ce username: " + username, ErrorCodes.UTILSATEUR_ALLREADY_EXIST);
        });
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        utilisateurRepository.deleteById(id);
    }

    //--Modification de mot de passe
    @Override
    public UtilisateurDto changerMotDePasseUtilisateurDto(ChangerMotDePasseUtilisateurDto dto) {
         validate(dto);
        Optional<Utilisateur>utilisateurOptional = utilisateurRepository.findById(dto.getId());
        if(utilisateurOptional.isPresent()){
            log.warn("Impossible de modifier le mot de passe avec un objet null");
            throw  new InvalidOperationException("Impossible de modifier le mot de passe avec un objet null", ErrorCodes.UTILSATEUR_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }
        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setMotDePasse(dto.getPassword());
        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }

    private  void validate(ChangerMotDePasseUtilisateurDto dto){
        if(dto == null){
            log.warn("impossible de modifier le mot de passe avec un objet vide");
            throw new InvalidOperationException("Aucune information n'a été fournie pour modifier le mot de passe", ErrorCodes.UTILSATEUR_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }
        if(dto.getId() == null){
            log.warn("impossible de modifier le mot de passe avec un id vide");
            throw new InvalidOperationException("Aucune information n'a été fournie pour modifier le mot de passe", ErrorCodes.UTILSATEUR_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }
        if(!StringUtils.hasLength(dto.getPassword()) || !StringUtils.hasLength(dto.getConfirmPassord())){
            log.warn("impossible de modifier le mot de passe avec un mot depasse de l'utilisateur");
            throw new InvalidOperationException("Impossible de modifier le mot de passe de l'utilisateur", ErrorCodes.UTILSATEUR_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }
        if(!dto.getPassword().equals(dto.getConfirmPassord())){
            log.warn("Impossible de modifier le mot de passe avec les deux mots de passe différent");
            throw new InvalidOperationException("Les mots de passent de l'utilisateur sont différents", ErrorCodes.UTILSATEUR_CHANGE_PASSWORD_OBJET_NOT_VALID);
        }
    }
}
