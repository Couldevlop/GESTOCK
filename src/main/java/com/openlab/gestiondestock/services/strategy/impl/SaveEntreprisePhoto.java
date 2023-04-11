package com.openlab.gestiondestock.services.strategy.impl;

import com.openlab.gestiondestock.model.Entreprise;
import com.openlab.gestiondestock.services.strategy.Strategy;

import java.io.InputStream;

public class SaveEntreprisePhoto implements Strategy<Entreprise> {
    @Override
    public Entreprise savePhoto(Integer id,InputStream photo, String titre) {
        return null;
    }
}
