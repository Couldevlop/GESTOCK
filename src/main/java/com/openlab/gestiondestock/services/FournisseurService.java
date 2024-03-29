package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.dto.FournisseurDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;


import java.math.BigDecimal;
import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto fournisseurDto);
    FournisseurDto findById(Integer id);
 //
    List<FournisseurDto> findAll();
    void delete(Integer id);

}
