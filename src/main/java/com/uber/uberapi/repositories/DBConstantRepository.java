package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uber.uberapi.models.DBConstant;

public interface DBConstantRepository extends JpaRepository<DBConstant, Long>{
    
}
