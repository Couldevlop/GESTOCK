package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT +"/commandes-clients")
public interface CommandeClientApi {
    @PostMapping(value = APP_ROOT +"commandesclients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);
    @GetMapping(value =APP_ROOT + "/commandesclients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id);

    @GetMapping(value =APP_ROOT + "/commandesclients/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code);

    @GetMapping(value = "/commandesclients", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT +"/commandesclients/{id}")
    ResponseEntity delete(@PathVariable Integer id);
}
