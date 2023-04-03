package com.openlab.gestiondestock.security.controller.api;

import com.openlab.gestiondestock.security.dto.LoginDto;
import com.openlab.gestiondestock.security.dto.RegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/authentication")
public interface UserApi {
    @Operation(summary = "Connexion d'un utilisateur", description = "Cette méthode permet de se connecter à l'application" )
    @PostMapping(value = APP_ROOT + "/authentication/user/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "l'objet Article a été ajouté")
    })
    ResponseEntity<String> authenticate(@RequestBody LoginDto loginDto);


    @Operation(summary = "Créer un utilisateur", description = "Cette méthode permet de créer" )
    @PostMapping(value = APP_ROOT + "/authentication/user/resgister", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "l'objet Article a été ajouté")
    })
    ResponseEntity<?> register (@RequestBody RegisterDto registerDto);
}
