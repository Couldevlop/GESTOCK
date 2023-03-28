package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto commandeClientDto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
}
