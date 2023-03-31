package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.MvtStkDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.MVTSTK_END_POINT;

@Tag(name = MVTSTK_END_POINT)
public interface MvtStkApi {
    @PostMapping(value = MVTSTK_END_POINT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto save(@RequestBody MvtStkDto  mvtStkDto);

    @GetMapping(value = MVTSTK_END_POINT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto findById(@PathVariable Integer id);

    @GetMapping(value = MVTSTK_END_POINT + "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto findByCode(@PathVariable String code);

    @GetMapping(value = MVTSTK_END_POINT, produces = MediaType.APPLICATION_JSON_VALUE)
    List<MvtStkDto> findAll();

    @DeleteMapping(value=MVTSTK_END_POINT + "/{id}")
    void delete(@PathVariable Integer id);
}
