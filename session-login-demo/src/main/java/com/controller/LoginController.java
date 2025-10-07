//package com.controller;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.entity.CustomerManager;
//import com.repository.CustomerManagerRepo;
//
//import java.util.Optional;
//
//@Controller
//public class LoginController {
//
//    @Autowired
//    private CustomerManagerRepo customerRepo;
//
//    @GetMapping("/")
//    public String loginPage(@RequestParam(value = "sessionExpired", required = false) String sessionExpired,
//                            Model model) {
//        if (sessionExpired != null) {
//            model.addAttribute("error", "Session expired. Please log in again.");
//        }
//        return "login"; // login.jsp
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        HttpSession session,
//                        Model model) {
//
//        Optional<CustomerManager> customerOpt = customerRepo.findByUsername(username);
//
//        if (customerOpt.isPresent()) {
//            CustomerManager customer = customerOpt.get();
//
//            if (customer.getLoggedin() == 1) {
//                model.addAttribute("error", "User already logged in from another session!");
//                return "login";
//            }
//
//            if (customer.getPassword().equals(password)) {
//                customer.setLoggedin(1);
//                customerRepo.save(customer);
//
//                // Save CustomerManager object in session
//                session.setAttribute("customer", customer);
//                return "redirect:/home";
//            } else {
//                model.addAttribute("error", "Invalid username or password!");
//                return "login";
//            }
//        } else {
//            model.addAttribute("error", "User does not exist!");
//            return "login";
//        }
//    }
//
//    @GetMapping("/home")
//    public String home() {
//        return "home"; // home.jsp
//    }
//}
