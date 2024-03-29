package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.controller.api.MvtStkApi;
import com.openlab.gestiondestock.model.dto.ClientDto;
import com.openlab.gestiondestock.model.dto.MvtStkDto;
import com.openlab.gestiondestock.services.MvtStkService;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStkController implements MvtStkApi {

    private final MvtStkService mvtStkService;

    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }


    @Override
    public BigDecimal sotckReelArticle(Integer idArticle) {
        return mvtStkService.sotckReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Integer idArticle) {
        return mvtStkService.mvtStkArticle(idArticle);
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return mvtStkService.entreeStock(dto);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return mvtStkService.sortieStock(dto);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {
        return mvtStkService.correctionStockPos(dto);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return mvtStkService.correctionStockNeg(dto);
    }
}
