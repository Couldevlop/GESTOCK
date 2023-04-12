package com.openlab.gestiondestock.services;


import com.openlab.gestiondestock.model.dto.MvtStkDto;


import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {
  BigDecimal sotckReelArticle(Integer idArticle);
  List<MvtStkDto> mvtStkArticle(Integer idArticle);
  MvtStkDto entreeStock(MvtStkDto dto);
  MvtStkDto sortieStock(MvtStkDto dto);
  MvtStkDto correctionStockPos(MvtStkDto dto);
  MvtStkDto correctionStockNeg(MvtStkDto dto);

}
