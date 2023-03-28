package com.openlab.gestiondestock.model;

import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_utilisateur")
public class Utilisateur extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Embedded
    private Adresse adresse;

    @Column(name="datedenaissance")
    private Instant DateDeNaissance;

    @Column(name="photo")
    private String photo;

    @Column(name="motdepasse")
    private String motDePasse;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "utilisateur")
    private List<Roles> roles;



}
