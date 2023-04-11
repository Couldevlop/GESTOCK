package com.openlab.gestiondestock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.openlab.gestiondestock.model.Client;

import java.io.InputStream;

public interface Strategy <T>{

    T savePhoto(Integer id, InputStream photo, String titre) throws FlickrException;


}
