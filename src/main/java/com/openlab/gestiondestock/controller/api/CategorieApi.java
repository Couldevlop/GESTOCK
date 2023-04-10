package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.CategorieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "categories")
public interface CategorieApi {
    @Operation(summary = "Enregistrer une categorie", description = "Cette méthode permet d'enregistrer ou de modifier une categorie" )
    @PostMapping(value = APP_ROOT + "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "l'objet Categorie a été ajouté"),
            @ApiResponse(responseCode = "200", description = "l'objet Categorie a été modifié"),
            @ApiResponse(responseCode = "400", description = "l'objet Categorie a été invalide")
    })
    CategorieDto save(@RequestBody CategorieDto categorieDto);


    @Operation(summary = "Chercher une categorie", description = "Cette méthode de rechercher une categorie par l'id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "l'objet categorie a été retrouvé"),
            @ApiResponse(responseCode = "404", description = "l'objet categorie est introuvable"),
            @ApiResponse(responseCode = "403", description = "Vous n'avez pas le droit d'appeler cette url")
    })
    @GetMapping(value = APP_ROOT + "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findById(@PathVariable Integer id);


    @GetMapping(value = APP_ROOT + "/categories/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Chercher une categorie", description = "Cette méthode permet de rechercher une categorie par le code" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "l'objet Categorie a été retrouvé"),
            @ApiResponse(responseCode = "403", description = "Vous n'avez pas le droit d'appeler cette url"),
            @ApiResponse(responseCode = "404", description = "l'objet Categorie est introuvable")
    })
    CategorieDto findByCode(@PathVariable String code);


    @GetMapping(value=APP_ROOT + "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Afficher une liste categorie", description = "Cette méthode permet d'afficher une liste de categorie" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affiche de la liste des categorie/liste vide"),
    })
    List<CategorieDto> findAll();


    @DeleteMapping(value="/categories/{id}")
    @Operation(summary = "Supprimer une categorie", description = "Cette méthode permet de supprimer un categorie" )
    @ApiResponse(responseCode = "200", description = "categorie supprimé")
    void delete(@PathVariable Integer id);
}
