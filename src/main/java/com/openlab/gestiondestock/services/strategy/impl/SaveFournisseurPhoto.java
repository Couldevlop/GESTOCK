package com.openlab.gestiondestock.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.Fournisseur;
import com.openlab.gestiondestock.model.dto.FournisseurDto;
import com.openlab.gestiondestock.services.FlickrService;
import com.openlab.gestiondestock.services.FournisseurService;
import com.openlab.gestiondestock.services.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("fournisseurStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveFournisseurPhoto implements Strategy<FournisseurDto> {
    private final FournisseurService fournisseurService;
    private final FlickrService flickrService;
    @Override
    public FournisseurDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        FournisseurDto fournisseurDto = fournisseurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de le fournisseur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        fournisseurDto.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseurDto);
    }
}
