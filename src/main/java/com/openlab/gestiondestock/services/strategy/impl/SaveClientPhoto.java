package com.openlab.gestiondestock.services.strategy.impl;

import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.services.strategy.Strategy;

import java.io.InputStream;

public class SaveClientPhoto implements Strategy<Client> {
    @Override
    public Client savePhoto(Integer id,InputStream photo, String titre) {
        return null;
    }
}
