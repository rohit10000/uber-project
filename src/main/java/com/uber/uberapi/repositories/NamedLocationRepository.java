package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.NamedLocation;

@Repository
public interface NamedLocationRepository extends JpaRepository<NamedLocation, Long>{
    
}
