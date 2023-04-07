package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.ClientDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "/clients")
public interface ClientApi {
    @PostMapping(value = APP_ROOT + "/clients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto);

    @GetMapping(value = APP_ROOT + "/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/clients/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> findByEmail(@PathVariable String email);

    @GetMapping(value = APP_ROOT + "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/{id}")
    ResponseEntity delete(@PathVariable Integer id);
}
