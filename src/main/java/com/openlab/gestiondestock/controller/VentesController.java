package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.VentesApi;
import com.openlab.gestiondestock.model.dto.VentesDto;
import com.openlab.gestiondestock.services.VenteService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {

    private final VenteService venteService;

    public VentesController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {
        return venteService.save(ventesDto);
    }

    @Override
    public VentesDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return venteService.findByCode(code);
    }

    @Override
    public List<VentesDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Integer id) {
     venteService.delete(id);
    }
}
