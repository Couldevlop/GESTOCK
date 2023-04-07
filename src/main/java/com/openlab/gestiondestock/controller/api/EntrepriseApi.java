package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.EntrepriseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;
import static com.openlab.gestiondestock.utils.Constants.ENTREPRISE_END_POINT;

@Tag(name = "entreprises")
public interface EntrepriseApi {
    @PostMapping(value = ENTREPRISE_END_POINT)
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = ENTREPRISE_END_POINT + "/{id}")
    EntrepriseDto findById(@PathVariable Integer id);

    @GetMapping(value = ENTREPRISE_END_POINT + "/{code}")
    EntrepriseDto findByNom(@PathVariable String code);

    @GetMapping(value=ENTREPRISE_END_POINT)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = ENTREPRISE_END_POINT + "/{id}")
    void delete(@PathVariable Integer id);
}
