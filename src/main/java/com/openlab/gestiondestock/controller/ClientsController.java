package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.ClientApi;
import com.openlab.gestiondestock.model.dto.ClientDto;
import com.openlab.gestiondestock.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientsController implements ClientApi {

    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }


    @Override
    public ResponseEntity<ClientDto> save(ClientDto clientDto) {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> findById(Integer id) {

        return ResponseEntity.ok(clientService.findById(id));
    }

    @Override
    public ResponseEntity<ClientDto> findByCode(String code) {
        return ResponseEntity.ok(clientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
