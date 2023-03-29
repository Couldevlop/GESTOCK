package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.ArticleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;


@Tag(name = APP_ROOT + "/articles")
public interface ArticleApi {
    @Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou de modifier un article" )
    @PostMapping(value = APP_ROOT + "/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "l'objet Article a été ajouté"),
            @ApiResponse(responseCode = "200", description = "l'objet Article a été modifié"),
            @ApiResponse(responseCode = "400", description = "l'objet Article a été invalide")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);


    @Operation(summary = "Chercher un article", description = "Cette méthode de rechercher un article par l'id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "l'objet Article a été retrouvé"),
            @ApiResponse(responseCode = "404", description = "l'objet Article est introuvable"),
            @ApiResponse(responseCode = "403", description = "Vous n'avez pas le droit d'appeler cette url")
    })
    @GetMapping(value = APP_ROOT + "/articles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable Integer id);


    @GetMapping(value = APP_ROOT + "/articles/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Chercher un article", description = "Cette méthode permet de rechercher un article par le code" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "l'objet Article a été retrouvé"),
            @ApiResponse(responseCode = "403", description = "Vous n'avez pas le droit d'appeler cette url"),
            @ApiResponse(responseCode = "404", description = "l'objet Article est introuvable")
    })
    ArticleDto findByCodeArticle(@PathVariable String code);


    @GetMapping(value=APP_ROOT + "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Afficher une liste article", description = "Cette méthode permet d'afficher une liste d'article" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affiche de la liste des articles/liste vide"),
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value="/articles/{id}")
    @Operation(summary = "Supprimer un article", description = "Cette méthode permet de supprimer un article" )
    @ApiResponse(responseCode = "200", description = "l'article supprimé")
    void delete(@PathVariable Integer id);
}
