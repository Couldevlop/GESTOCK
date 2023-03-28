package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournissuerService {
    CommandeFournisseurDto save(CommandeFournisseurDto fournisseurDto);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto findByCode(String code);
    List<CommandeFournisseurDto> findAll();
    void delete(Integer id);
}
