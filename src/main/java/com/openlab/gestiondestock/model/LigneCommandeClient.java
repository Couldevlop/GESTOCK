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
@Table(name = "st_lignecommandeclient")
public class LigneCommandeClient extends  AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idcommandeClient")
    private CommandeClient commandeClient;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @Column(name = "peixunitaire")
    private  BigDecimal prixUnitaire;
}
