package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{
    
}
