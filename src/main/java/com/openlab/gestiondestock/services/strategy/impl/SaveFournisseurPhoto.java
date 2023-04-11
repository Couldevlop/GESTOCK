package com.openlab.gestiondestock.services.strategy.impl;

import com.openlab.gestiondestock.model.Fournisseur;
import com.openlab.gestiondestock.services.strategy.Strategy;

import java.io.InputStream;

public class SaveFournisseurPhoto implements Strategy<Fournisseur> {
    @Override
    public Fournisseur savePhoto(Integer id,InputStream photo, String titre) {
        return null;
    }
}
