package com.service;

import com.entity.Product;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
// Simple in-memory cart per session (map productId -> qty)
// We'll store the Map in session attribute "cart"
	public Map<Long, Integer> getCartMap(Object cartObj) {
		if (cartObj instanceof Map) {
			return (Map<Long, Integer>) cartObj;
		}
		return new HashMap<>();
	}
}
