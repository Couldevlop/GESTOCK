package com.openlab.gestiondestock.model;

import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "st_utilisateur")
public class Utilisateur extends AbstractEntity implements Serializable, UserDetails {

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

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "identreprise")
    private Entreprise entreprise;


    @ManyToMany(fetch = FetchType.EAGER  , cascade = CascadeType.PERSIST)
    private List<Roles> roles;


    public Utilisateur(String email, String password, List<Roles>roles){
        this.email = email;
        this.motDePasse = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName().name())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.motDePasse;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
