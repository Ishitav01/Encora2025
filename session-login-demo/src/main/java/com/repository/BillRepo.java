package com.repository;

import 
org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BillManager;

public interface BillRepo extends JpaRepository<BillManager, Long> {

}
