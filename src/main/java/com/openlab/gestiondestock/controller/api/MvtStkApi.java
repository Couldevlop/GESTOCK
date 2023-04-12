package com.openlab.gestiondestock.controller.api;

import com.openlab.gestiondestock.model.dto.MvtStkDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.openlab.gestiondestock.utils.Constants.MVTSTK_END_POINT;

@Tag(name = "mvstk")
public interface MvtStkApi {
    @GetMapping(value = MVTSTK_END_POINT + "/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal sotckReelArticle(@PathVariable Integer idArticle);
    @GetMapping(value = MVTSTK_END_POINT + "/filtre/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MvtStkDto> mvtStkArticle(@PathVariable Integer idArticle);
    @PostMapping(value = MVTSTK_END_POINT + "/entree", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto entreeStock(@RequestBody MvtStkDto dto);
    @PostMapping(value = MVTSTK_END_POINT + "/sortie", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto sortieStock(@RequestBody MvtStkDto dto);

    @PostMapping(value = MVTSTK_END_POINT + "/correctionpositive", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto correctionStockPos(@RequestBody MvtStkDto dto);

    @PostMapping(value = MVTSTK_END_POINT + "/correctionnegative", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto correctionStockNeg(@RequestBody MvtStkDto dto);

}
