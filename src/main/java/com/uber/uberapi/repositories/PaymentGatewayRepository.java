package com.uber.uberapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.PaymentGateway;

@Repository
public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, Long>{
    
}
