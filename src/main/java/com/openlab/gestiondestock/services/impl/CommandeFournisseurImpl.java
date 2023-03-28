package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import com.openlab.gestiondestock.services.CommandeFournissuerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommandeFournisseurImpl implements CommandeFournissuerService {
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto fournisseurDto) {
        return null;
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        return null;
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return null;
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
