package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.CustomerManager;

import java.util.Optional;

public interface CustomerManagerRepo extends JpaRepository<CustomerManager, Integer> {
    Optional<CustomerManager> findByUsername(String username);
}
