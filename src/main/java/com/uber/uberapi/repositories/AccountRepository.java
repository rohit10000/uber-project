package com.uber.uberapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.uberapi.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findFirstByUsername(String username);
}
