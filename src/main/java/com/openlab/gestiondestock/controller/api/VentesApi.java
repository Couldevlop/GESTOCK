package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.VentesDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.*;

@Tag(name = "/ventes")
public interface VentesApi {
    @PostMapping(value = VENTE_END_POINT)
    VentesDto save(@RequestBody VentesDto ventesDto);

    @GetMapping(value = ENTREPRISE_END_POINT+ "/{id}")
    VentesDto findById(@PathVariable Integer id);

    @GetMapping(value = ENTREPRISE_END_POINT+ "/{code")
    VentesDto findByCode(@PathVariable String code);

    @GetMapping(value = ENTREPRISE_END_POINT)
    List<VentesDto> findAll();

    @GetMapping(value = ENTREPRISE_END_POINT+ "/{id}")
    void delete(@PathVariable Integer id);
}
