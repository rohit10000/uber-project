package com.uber.uberapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{
    Optional<Driver> findFirstByAccount_Username(String username);
}
