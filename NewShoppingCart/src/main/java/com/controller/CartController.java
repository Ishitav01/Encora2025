package com.controller;

import com.entity.Product;
import com.repository.ProductRepo;
import com.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Map<Long, Integer> cart = cartService.getCartMap(session.getAttribute("cart"));
        List<Map<String, Object>> cartItems = new ArrayList<>();
        double total = 0;

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Product p = productRepo.findById((long) entry.getKey().intValue()).orElse(null);
            if (p != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("product", p);
                item.put("quantity", entry.getValue());
                cartItems.add(item);
                total += p.getPrice() * entry.getValue();
            }
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Map<Long, Integer> cart = cartService.getCartMap(session.getAttribute("cart"));
        cart.put(id, cart.getOrDefault(id, 0) + 1);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
}
