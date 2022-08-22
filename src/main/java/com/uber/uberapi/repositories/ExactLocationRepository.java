package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.ExactLocation;

@Repository
public interface ExactLocationRepository extends JpaRepository<ExactLocation, Long>{
    
}
