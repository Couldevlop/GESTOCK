package com.openlab.gestiondestock.model;

import com.openlab.gestiondestock.enums.SourceMvtStk;
import com.openlab.gestiondestock.enums.TypeMvtStk;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_mvntstk")
public class MvtStk extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name="datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @Column(name="sourcemvtstk")
    private SourceMvtStk sourceMvtStk;

    @Column(name = "typemvtstk")
    private TypeMvtStk typeMvtStk;
}
