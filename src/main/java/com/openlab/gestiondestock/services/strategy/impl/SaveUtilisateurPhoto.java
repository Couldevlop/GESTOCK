package com.openlab.gestiondestock.services.strategy.impl;

import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.services.strategy.Strategy;

import java.io.InputStream;

public class SaveUtilisateurPhoto implements Strategy<Utilisateur> {
    @Override
    public Utilisateur savePhoto(Integer id,InputStream photo, String titre) {
        return null;
    }


}
