package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
    
}
