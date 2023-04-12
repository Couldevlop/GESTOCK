package com.openlab.gestiondestock.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.dto.EntrepriseDto;
import com.openlab.gestiondestock.services.EntrepriseService;
import com.openlab.gestiondestock.services.FlickrService;
import com.openlab.gestiondestock.services.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("entrepriseStrategy")
@Slf4j

public class SaveEntreprisePhoto implements Strategy<EntrepriseDto> {
    private final FlickrService flickrService;
    private final EntrepriseService entrepriseService;

    public SaveEntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
        this.flickrService = flickrService;
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        EntrepriseDto entrepriseDto = entrepriseService.findById(id);
        String urlPhoto =flickrService.savePhoto(photo,titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        entrepriseDto.setPhoto(urlPhoto);
        return entrepriseService.save(entrepriseDto);
    }
}
