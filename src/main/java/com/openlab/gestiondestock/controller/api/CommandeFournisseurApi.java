package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PatchMapping(value =APP_ROOT + "/commandesfournisseur/quantite/{idCommande}/{idLignCmd}/{quantite}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurDto>updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
                                                            @PathVariable("idLignCmd") Integer idLignCmd,
                                                            @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value =APP_ROOT + "/commandesfournisseur/etat/{id}/{etatcommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurDto> updateEtaCommande(@PathVariable("id") Integer idCommande, @PathVariable EtatCommande etatCommande);
    @PatchMapping(value =APP_ROOT + "/commandesfournisseur/{id}/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurDto> updateFournisseur(@PathVariable("id") Integer idCommande, @PathVariable Integer idFour);
    @PatchMapping(value =APP_ROOT + "/commandesfournisseur/{idComande}/{idLigneCmd}/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurDto> updateArticle(@PathVariable Integer idCommande, @PathVariable Integer idLigneCmd, @PathVariable Integer idArticle);
    @DeleteMapping(value = APP_ROOT +"/commandesfournisseur/{idCommande}/{idLigneCmd}")
    ResponseEntity deleteArticle(@PathVariable Integer idCommande, @PathVariable Integer idLigneCmd);

    @GetMapping(value =APP_ROOT + "/commandesfournisseur/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCommandeFournisseurDto>>findAllLigneCommandeClientBycommandeClientId(@PathVariable Integer idCommande);
}
