package com.openlab.gestiondestock.model;

import com.openlab.gestiondestock.enums.EtatCommande;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_commandeclient")
public class CommandeClient extends AbstractEntity{
    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;

    @Column(name ="etatcommande" )
    private EtatCommande etatCommande;

    @Embedded
    private Adresse adresse;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy = "commandeClient")
    private List<LigneCommandeClient> commandeClient;
}
