package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long>{
    
}
