package com.openlab.gestiondestock.controller;

import com.flickr4java.flickr.FlickrException;
import com.openlab.gestiondestock.controller.api.PhotoApi;
import com.openlab.gestiondestock.services.strategy.StrategyPhotoContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PhotoController implements PhotoApi {
    private StrategyPhotoContext strategyPhotoContext;

    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return strategyPhotoContext.savePhoto(context,id,photo.getInputStream(),title);
    }
}
