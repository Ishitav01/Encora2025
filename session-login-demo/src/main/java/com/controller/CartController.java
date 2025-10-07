package com.controller;

import com.entity.Product;
import com.repository.ProductRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam int productId, @RequestParam(defaultValue = "1") Integer qty,
                            HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        cart.put(productId, cart.getOrDefault(productId, 0) + qty);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        Map<Product, Integer> display = new HashMap<>();
        double total = 0.0;

        if (cart != null) {
            for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
                Optional<Product> p = productRepo.findById(e.getKey());
                if (p.isPresent()) {
                    display.put(p.get(), e.getValue());
                    total += p.get().getPrice() * e.getValue();
                }
            }
        }

        model.addAttribute("items", display);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam int productId, HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart != null) {
            cart.remove(productId);
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }
}
