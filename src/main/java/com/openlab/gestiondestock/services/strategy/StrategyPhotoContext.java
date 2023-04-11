package com.openlab.gestiondestock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.services.strategy.impl.*;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.io.InputStream;

@Service

public class StrategyPhotoContext {
    private BeanFactory beanFactory;
    private Strategy strategy;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Setter
   private String context;

   public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
       detrminContext(context);
       return strategy.savePhoto(id,  photo,title);
   }


    private void detrminContext(String context){
        String beanName = context+"Strategy";
        switch (context){
            case "article":
                strategy = beanFactory.getBean("beanName", SaveArticlePhoto.class);
                break;
            case "client":
                strategy = beanFactory.getBean("beanName", SaveClientPhoto.class);
                break;
            case "fournisseur":
                strategy = beanFactory.getBean("beanName", SaveFournisseurPhoto.class);
                break;
            case "utilisateur":
                strategy = beanFactory.getBean("beanName", SaveUtilisateurPhoto.class);
                break;
            case "entreprise":
                strategy = beanFactory.getBean("beanName", SaveEntreprisePhoto.class);
                break;
            default: throw new InvalidOperationException("contexte inconnu", ErrorCodes.UNKNOW_CONTEXT);
        }
    }
}
