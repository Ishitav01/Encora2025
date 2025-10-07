package com.repository;

import com.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
	List<Product> findByShop(String shop);
}
