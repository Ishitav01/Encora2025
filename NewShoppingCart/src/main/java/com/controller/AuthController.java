package com.controller;

import com.entity.User;
import com.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String loginPage(Model model,
            @RequestParam(value = "sessionExpired", required = false) String sessionExpired) {
        if (sessionExpired != null) {
            model.addAttribute("error", "Session expired. Please log in again.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<User> existing = userRepo.findByUsername(username);
        if (existing.isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setLoggedIn(0);
        userRepo.save(u);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
            Model model) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "User does not exist!");
            return "login";
        }
        User user = userOpt.get();
        if (user.getLoggedIn() == 1) {
            model.addAttribute("error", "User already logged in from another session!");
            return "login";
        }
        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
        user.setLoggedIn(1);
        userRepo.save(user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("cart", new java.util.HashMap<Long, Integer>());
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            userRepo.findByUsername(username).ifPresent(user -> {
                user.setLoggedIn(0);
                userRepo.save(user);
            });
        }
        session.invalidate();
        return "redirect:/?sessionExpired=true";
    }
}
