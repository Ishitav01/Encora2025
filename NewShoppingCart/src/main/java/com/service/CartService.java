package com.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    @SuppressWarnings("unchecked")
    public Map<Long, Integer> getCartMap(Object sessionCart) {
        if (sessionCart == null) {
            return new HashMap<>();
        }
        return (Map<Long, Integer>) sessionCart;
    }
}
