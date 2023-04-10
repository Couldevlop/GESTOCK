package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.LigneVenteDto;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "ligneVentes")
public interface LigneVentesApi {
    LigneVenteDto save(LigneVenteDto ligneVenteDto);
    LigneVenteDto findById(Integer id);
    LigneVenteDto findByCode(String code);
    List<LigneVenteDto> findAll();
    void delete(Integer id);
}
