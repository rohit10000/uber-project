package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    
}
