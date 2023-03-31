package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.FournisseurDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;
import static com.openlab.gestiondestock.utils.Constants.FOURNISSEUR_END_POINT;

@Tag(name = FOURNISSEUR_END_POINT)
public interface FournisseurApi {
    @PostMapping(value = FOURNISSEUR_END_POINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = FOURNISSEUR_END_POINT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> findById(@PathVariable Integer id);

    @GetMapping(value = FOURNISSEUR_END_POINT + "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> findByCode(@PathVariable String code);

    @GetMapping(value = FOURNISSEUR_END_POINT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<FournisseurDto>> findAll();

    @DeleteMapping(value=FOURNISSEUR_END_POINT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity delete(Integer id);
}
