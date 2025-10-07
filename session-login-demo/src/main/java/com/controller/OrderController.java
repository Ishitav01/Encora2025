package com.controller;

import com.entity.BillManager;
import com.entity.ItemTransaction;
import com.entity.Product;
import com.repository.BillRepo;
import com.repository.ItemTransactionRepo;
import com.repository.ProductRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private ItemTransactionRepo itemTransactionRepo;

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/?sessionExpired=true";
        }

        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("error", "Cart is empty");
            return "cart";
        }

        BillManager bill = new BillManager();
        bill.setCreatedAt(new Date());

        List<ItemTransaction> items = new ArrayList<>();
        double total = 0.0;

        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            Optional<Product> prodOpt = productRepo.findById(e.getKey());
            if (prodOpt.isPresent()) {
                Product p = prodOpt.get();
                ItemTransaction it = new ItemTransaction();
                it.setProductId(p.getItemno()); // use int ID
                it.setQty(e.getValue());
                it.setPrice(p.getPrice());
                it.setBill(bill);
                items.add(it);
                total += p.getPrice() * e.getValue();
            }
        }

        bill.setTotal(total);
        bill.setItems(items);

        BillManager saved = billRepo.save(bill);
        // CascadeType.ALL ensures ItemTransaction objects are saved automatically

        // Clear cart
        session.setAttribute("cart", new HashMap<Integer, Integer>());
        model.addAttribute("bill", saved);
        model.addAttribute("items", items);

        return "invoice";
    }
}
