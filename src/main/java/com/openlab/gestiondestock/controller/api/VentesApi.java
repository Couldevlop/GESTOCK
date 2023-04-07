package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.VentesDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.*;

@Tag(name = "/ventes")
public interface VentesApi {
    @PostMapping(value = VENTE_END_POINT)
    VentesDto save(@RequestBody VentesDto ventesDto);

    @GetMapping(value = VENTE_END_POINT + "/{id}")
    VentesDto findById(@PathVariable Integer id);

    @GetMapping(value = VENTE_END_POINT+ "/{code}")
    VentesDto findByCode(@PathVariable String code);

    @GetMapping(value = VENTE_END_POINT)
    List<VentesDto> findAll();

    @DeleteMapping(value = VENTE_END_POINT+ "/{id}")
    void delete(@PathVariable Integer id);
}
