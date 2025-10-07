//package com.controller;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import com.entity.CustomerManager;
//import com.repository.CustomerManagerRepo;
//
//@Controller
//public class LogoutController {
//
//    @Autowired
//    private CustomerManagerRepo customerRepo;
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        CustomerManager customer = (CustomerManager) session.getAttribute("customer");
//
//        if (customer != null) {
//            customer.setLoggedin(0);
//            customerRepo.save(customer);
//        }
//
//        session.invalidate(); // destroy session
//        return "redirect:/?sessionExpired=true";
//    }
//}
