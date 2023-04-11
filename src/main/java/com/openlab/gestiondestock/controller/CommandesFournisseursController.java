package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.CommandeFournisseurApi;
import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;
import com.openlab.gestiondestock.services.CommandeFournissuerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandesFournisseursController implements CommandeFournisseurApi {

    private final CommandeFournissuerService commandeFournissuerService;

    public CommandesFournisseursController(CommandeFournissuerService commandeFournissuerService) {
        this.commandeFournissuerService = commandeFournissuerService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(commandeFournissuerService.save(commandeFournisseurDto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(commandeFournissuerService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(commandeFournissuerService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournissuerService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeFournissuerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateQuantiteCommande(Integer idCommande, Integer idLignCmd, BigDecimal quantite) {
        return ResponseEntity.ok(commandeFournissuerService.updateQuantiteCommande(idCommande,idLignCmd,quantite));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateEtaCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeFournissuerService.updateEtaCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer idCommande, Integer idFour) {
        return ResponseEntity.ok(commandeFournissuerService.updateFournisseur(idCommande,idFour));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateArticle(Integer idCommande, Integer idLigneCmd, Integer idArticle) {
        return ResponseEntity.ok(commandeFournissuerService.updateArticle(idCommande,idLigneCmd,idArticle));
    }

    @Override
    public ResponseEntity deleteArticle(Integer idCommande, Integer idLigneCmd) {
        return ResponseEntity.ok(commandeFournissuerService.deleteArticle(idCommande,idLigneCmd));
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLigneCommandeClientBycommandeClientId(Integer idCommande) {
        return ResponseEntity.ok(commandeFournissuerService.findAllLigneCommandeFournisseurBycommandeFournisseurId(idCommande));
    }
}
