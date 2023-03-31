package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.VentesDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/ventes")
public interface VentesApi {
    VentesDto save(@RequestBody VentesDto ventesDto);
    VentesDto findById(@PathVariable Integer id);
    VentesDto findByCode(@PathVariable String code);
    List<VentesDto> findAll();
    void delete(@PathVariable Integer id);
}
