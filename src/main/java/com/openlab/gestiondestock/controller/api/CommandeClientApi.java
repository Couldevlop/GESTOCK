package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "/commandes-clients")
public interface CommandeClientApi {
    @PostMapping(value = APP_ROOT +"commandesclients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);
    @GetMapping(value =APP_ROOT + "/commandesclients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id);
    @PatchMapping(value =APP_ROOT + "/commandesclients/quantite/{idCommande}/{idLignCmd}/{quantite}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto>updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
                                                            @PathVariable("idLignCmd") Integer idLignCmd,
                                                            @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value =APP_ROOT + "/commandesclients/etat/{id}/{etatcommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> updateEtaClient(@PathVariable("id") Integer idCommande, @PathVariable EtatCommande etatCommande);
    @PatchMapping(value =APP_ROOT + "/commandesclients/{id}/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("id") Integer idCommande, @PathVariable Integer idClient);

    @GetMapping(value =APP_ROOT + "/commandesclients/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code);

    @GetMapping(value = "/commandesclients", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT +"/commandesclients/{id}")
    ResponseEntity delete(@PathVariable Integer id);
}
