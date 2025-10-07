package com.repository;

import com.entity.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTransactionRepo extends JpaRepository<ItemTransaction, Long> {}
