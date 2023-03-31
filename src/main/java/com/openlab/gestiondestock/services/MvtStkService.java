package com.openlab.gestiondestock.services;


import com.openlab.gestiondestock.model.dto.MvtStkDto;


import java.util.List;

public interface MvtStkService {
    MvtStkDto save(MvtStkDto mvtStkDto);
     MvtStkDto findById(Integer id);
    MvtStkDto findByCode(String code);
    List<MvtStkDto> findAll();
    void delete(Integer id);
}
