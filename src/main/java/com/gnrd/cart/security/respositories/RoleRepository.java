package com.gnrd.cart.security.respositories;

import java.util.Optional;

import com.gnrd.cart.security.entities.Role;
import com.gnrd.cart.security.enums.RoleList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleList roleName);

}
