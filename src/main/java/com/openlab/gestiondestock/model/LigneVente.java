package com.openlab.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_lignevente")
public class LigneVente extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idventes")
    private Ventes ventes;

    @Column(name="quantite")
    private BigDecimal quantite;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @Column(name = "peixunitaire")
    private  BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
}
