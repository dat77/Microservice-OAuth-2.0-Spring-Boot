package com.onix.msoauth.repository;

import com.onix.msoauth.entities.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Integer> {
    Optional<SecurityUser> findByUsername(String userName);
}
