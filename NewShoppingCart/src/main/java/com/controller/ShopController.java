package com.controller;

import com.entity.BillManager;
import com.entity.ItemTransaction;
import com.entity.Product;
import com.repository.BillManagerRepo;
import com.repository.ItemTransactionRepo;
import com.repository.ProductRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ShopController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private BillManagerRepo billRepo;

    @Autowired
    private ItemTransactionRepo itemRepo;

    // ======================
    // HOME
    // ======================
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        model.addAttribute("username", session.getAttribute("username"));
        return "home";
    }

    // ======================
    // SHOPS
    // ======================
    @GetMapping("/shop1")
    public String shop1(Model model, HttpSession session) {
        model.addAttribute("products", productRepo.findByShop("Shop 1"));
        model.addAttribute("shopName", "Shop 1");
        session.setAttribute("currentShop", "/shop1");
        return "shop";
    }

    @GetMapping("/shop2")
    public String shop2(Model model, HttpSession session) {
        model.addAttribute("products", productRepo.findByShop("Shop 2"));
        model.addAttribute("shopName", "Shop 2");
        session.setAttribute("currentShop", "/shop2");
        return "shop";
    }

    @GetMapping("/shop3")
    public String shop3(Model model, HttpSession session) {
        model.addAttribute("products", productRepo.findByShop("Shop 3"));
        model.addAttribute("shopName", "Shop 3");
        session.setAttribute("currentShop", "/shop3");
        return "shop";
    }

    // ======================
    // ADD TO CART
    // ======================
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam Integer quantity,
                            HttpSession session) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
        session.setAttribute("cart", cart);

        String currentShop = (String) session.getAttribute("currentShop");
        if (currentShop != null) {
            return "redirect:" + currentShop;
        }
        return "redirect:/home";
    }

    // ======================
    // INVOICE PAGE (Review Cart)
    // ======================
    @GetMapping("/invoice")
    public String invoice(HttpSession session, Model model) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("error", "Your cart is empty!");
            return "invoice";
        }

        List<InvoiceItem> items = new ArrayList<>();
        double total = 0;

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Long productId = entry.getKey();
            Integer qty = entry.getValue();
            Product product = productRepo.findById(productId).orElse(null);
            if (product != null) {
                double itemTotal = product.getPrice() * qty;
                total += itemTotal;
                items.add(new InvoiceItem(product.getName(), product.getShop(), product.getPrice(), qty, itemTotal));
            }
        }

        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("username", session.getAttribute("username"));

        // Don’t clear cart here, just show invoice
        return "invoice";
    }

    @PostMapping("/complete-purchase")
    public String completePurchase(HttpSession session) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/invoice";
        }

        double total = 0;
        List<ItemTransaction> transactions = new ArrayList<>();

        // Create new bill
        BillManager bill = new BillManager();
        bill.setUsername((String) session.getAttribute("username"));
        bill = billRepo.save(bill);

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Long productId = entry.getKey();
            Integer qty = entry.getValue();
            Product product = productRepo.findById(productId).orElse(null);
            if (product != null) {
                double itemTotal = product.getPrice() * qty;
                total += itemTotal;

                ItemTransaction it = new ItemTransaction();
                it.setProductId(product.getId());
                it.setProductName(product.getName());
                it.setShop(product.getShop());
                it.setQuantity(qty);
                it.setPrice(product.getPrice());
                it.setBill(bill);
                transactions.add(it);
            }
        }

        bill.setTotal(total);
        bill.setItems(transactions);
        billRepo.save(bill);
        itemRepo.saveAll(transactions);

        // Clear cart
        session.setAttribute("cart", new HashMap<Long, Integer>());

        return "complete";
    }


    // ======================
    // DTO for Invoice Items
    // ======================
    public static class InvoiceItem {
        private String name;
        private String shop;
        private double price;
        private int quantity;
        private double total;

        public InvoiceItem(String name, String shop, double price, int quantity, double total) {
            this.name = name;
            this.shop = shop;
            this.price = price;
            this.quantity = quantity;
            this.total = total;
        }

        public String getName() { return name; }
        public String getShop() { return shop; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public double getTotal() { return total; }
    }
}
