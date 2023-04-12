package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {

    @Query("select sum(m.quantite) from MvtStk m where m.id = :idArticle")
    BigDecimal sotckReelArticle(@Param("idArticle") Integer idArticle);

    List<MvtStk> findAllByArticleId(Integer idArticle);
}
