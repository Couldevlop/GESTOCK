package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.model.dto.ClientDto;
import com.openlab.gestiondestock.repository.ClientRepository;
import com.openlab.gestiondestock.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        return null;
    }

    @Override
    public ClientDto findById(Integer id) {
        return null;
    }

    @Override
    public ClientDto findByCode(String code) {
        return null;
    }

    @Override
    public List<ClientDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
