package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
        
}
