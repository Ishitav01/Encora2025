package com.config;

import com.repository.UserRepo;
import com.entity.User;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionListener implements HttpSessionListener {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute("username");
        if (username != null) {
            Optional<User> userOpt = userRepo.findByUsername(username);
            userOpt.ifPresent(user -> {
                user.setLoggedIn(0);
                userRepo.save(user);
                System.out.println("User '" + username + "' logged out automatically due to session timeout.");
            });
        }
    }
}
