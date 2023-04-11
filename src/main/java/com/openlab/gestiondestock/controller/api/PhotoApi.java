package com.openlab.gestiondestock.controller.api;

import com.flickr4java.flickr.FlickrException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "photo")
public interface PhotoApi {

    @PostMapping(value = APP_ROOT + "/photo/{id}/{title}/{context}")
    Object savePhoto(String context, Integer id, @RequestPart("file") MultipartFile photo, String title) throws IOException, FlickrException;
}
