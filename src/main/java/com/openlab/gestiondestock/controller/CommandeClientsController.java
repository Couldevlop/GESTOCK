package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.CommandeClientApi;
import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.services.CommandeClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientsController implements CommandeClientApi {

    private final CommandeClientService commandeClientService;

    public CommandeClientsController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto commandeClientDto) {
        return ResponseEntity.ok(commandeClientService.save(commandeClientDto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtaCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(commandeClientService.updateEtaCommande(idCommande,etatCommande));
    }

    public ResponseEntity<CommandeClientDto> updateClient(Integer idCommande, Integer idClient) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(commandeClientService.updateClient(idCommande,idClient));
    }

    public ResponseEntity<CommandeClientDto> updateArticle(Integer idCommande, Integer idLigneCmd, Integer idArticle) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(commandeClientService.updateArticle(idCommande,idLigneCmd,idArticle));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Integer idCommande, Integer idLignCmd , BigDecimal quantite) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(commandeClientService.updateQuantiteCommande(idCommande,idLignCmd, quantite));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCommandeClientDto>> findAllLigneCommandeClientBycommandeClientId(Integer idCommande) {
        return ResponseEntity.ok(commandeClientService.findAllLigneCommandeClientBycommandeClientId(idCommande));
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteArticle(Integer idCommande, Integer idLigneCmd) {
        commandeClientService.deleteArticle(idCommande, idLigneCmd);
        return ResponseEntity.ok().build();
    }
}
