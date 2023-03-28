package com.openlab.gestiondestock.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_roles")
public class Roles extends AbstractEntity{
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "rolename")
    private String roleName;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name="idutilisateur")
    private Utilisateur utilisateur;
}