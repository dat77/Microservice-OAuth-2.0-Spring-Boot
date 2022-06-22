package com.onix.msoauth.repository;

import com.onix.msoauth.entities.Role;
import com.onix.msoauth.entities.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);
}
