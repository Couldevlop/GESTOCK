package com.openlab.gestiondestock.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import com.openlab.gestiondestock.services.FlickrService;
import com.openlab.gestiondestock.services.UtilisateurService;
import com.openlab.gestiondestock.services.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("utilisateurStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {
    private final FlickrService flickrService;
    private final UtilisateurService utilisateurService;
    @Override
    public UtilisateurDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        UtilisateurDto utilisateurDto = utilisateurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'utilisateur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        utilisateurDto.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateurDto);
    }


}
