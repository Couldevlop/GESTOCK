package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto clientDto);
    ClientDto findById(Integer id);
    ClientDto findByEmail(String email);
    List<ClientDto> findAll();
    void delete(Integer id);
}
