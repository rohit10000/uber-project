package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
