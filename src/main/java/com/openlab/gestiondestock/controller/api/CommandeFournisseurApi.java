package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.*;

@Tag(name = "commande-fournisseurs")
public interface CommandeFournisseurApi {

    @PostMapping(value = COMMANDE_FOURNISSEUR_END_POINT)
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value = COMMANDE_FOURNISSEUR_END_POINT+"/{id}")
    ResponseEntity<CommandeFournisseurDto>  findById(@PathVariable Integer id);

    @GetMapping(value = COMMANDE_FOURNISSEUR_END_POINT + "/{code}")
    ResponseEntity<CommandeFournisseurDto>  findByCode(@PathVariable String code);

    @GetMapping(value = COMMANDE_FOURNISSEUR_END_POINT)
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @DeleteMapping(value = COMMANDE_FOURNISSEUR_END_POINT + "/{id}")
    ResponseEntity delete(@PathVariable Integer id);
}
