package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.enums.RoleName;
import com.openlab.gestiondestock.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByRoleName(RoleName roleName);
}
