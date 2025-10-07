package com.component;

import com.repository.UserRepo;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionListener implements HttpSessionListener {

    private static UserRepo staticRepo;

    @Autowired
    public void init(UserRepo userRepo) {
        staticRepo = userRepo;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute("username");
        if (username != null && staticRepo != null) {
            Optional<com.entity.User> userOpt = staticRepo.findByUsername(username);
            userOpt.ifPresent(user -> {
                user.setLoggedIn(0);
                staticRepo.save(user);
                System.out.println("Session expired. User " + username + " set to logged_out.");
            });
        }
    }
}