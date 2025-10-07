package com.controller;

import com.entity.Product;
import com.repository.ProductRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class ShopController {
	@Autowired
	private ProductRepo productRepo;

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		return "home";
	}

	@GetMapping("/shops")
	public String shops(Model model) {
// show list of shops (distinct shop names)
		List<Product> all = productRepo.findAll();
		java.util.Set<String> shops = new java.util.LinkedHashSet<>();
		for (Product p : all)
			shops.add(p.getShop());
		model.addAttribute("shops", shops);
		return "shops";
	}

	@GetMapping("/shop/{shopName}")
	public String shop(@PathVariable String shopName, Model model) {
		List<Product> products = productRepo.findByShop(shopName);
		model.addAttribute("products", products);
		model.addAttribute("shopName", shopName);
		return "shop";
	}
}
