package com.openlab.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_entreprise")
public class Entreprise extends AbstractEntity {

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Embedded
    private Adresse adresse;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "codefiscal")
    private String codeFiscal;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name="siteweb")
    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateur;
}
