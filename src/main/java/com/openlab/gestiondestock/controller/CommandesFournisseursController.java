package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.CommandeFournisseurApi;
import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import com.openlab.gestiondestock.services.CommandeFournissuerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
