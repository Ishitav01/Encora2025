package com.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findByShop(String shop);
	
}
