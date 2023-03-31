package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.UtilisateurDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/utilisateurs")
public interface UtilisateursApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/{id}")
    void delete(@PathVariable Integer id);
}
