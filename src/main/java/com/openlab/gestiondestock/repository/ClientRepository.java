package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);

}
