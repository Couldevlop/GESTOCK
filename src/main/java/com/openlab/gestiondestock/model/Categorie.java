package com.openlab.gestiondestock.model;

import com.openlab.gestiondestock.model.dto.CategorieDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_categorie")
public class Categorie extends AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "categorie")
    private List<Article> articles;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;


}
