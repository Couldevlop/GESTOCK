package com.openlab.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "st_ventes")
public class Ventes extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "ventes")
    private List<LigneVente> ligneVente;

    @Column(name="datevente")
    private Instant dateVente;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @Column(name = "commentaire")
    private String commentaire;
}
