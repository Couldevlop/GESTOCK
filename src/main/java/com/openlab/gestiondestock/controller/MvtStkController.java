package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.MvtStkApi;
import com.openlab.gestiondestock.model.dto.ClientDto;
import com.openlab.gestiondestock.model.dto.MvtStkDto;
import com.openlab.gestiondestock.services.MvtStkService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MvtStkController implements MvtStkApi {

    private final MvtStkService mvtStkService;

    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    @Override
    public MvtStkDto save(MvtStkDto mvtStkDto) {
        return mvtStkService.save(mvtStkDto);
    }

    @Override
    public MvtStkDto findById(Integer id) {
        return mvtStkService.findById(id);
    }

    @Override
    public MvtStkDto findByCode(String code) {
        return mvtStkService.findByCode(code);
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkService.findAll();
    }

    @Override
    public void delete(Integer id) {
     mvtStkService.delete(id);
    }
}
