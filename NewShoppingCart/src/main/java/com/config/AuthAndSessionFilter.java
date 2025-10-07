package com.config;

import com.repository.UserRepo;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@WebFilter("/*")
public class AuthAndSessionFilter implements Filter {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

        // Allowed pages (public)
        boolean allowed = uri.equals("/") || uri.equals("/login") || uri.equals("/register") ||
                uri.startsWith(req.getContextPath() + "/css") ||
                uri.startsWith(req.getContextPath() + "/js") ||
                uri.startsWith(req.getContextPath() + "/images") ||
                uri.contains("/static/");

        // Check if session exists and user is logged in
        boolean loggedIn = (session != null && session.getAttribute("username") != null);

        if (!loggedIn && !allowed) {
            // Session expired or user not logged in
            if (session != null) {
                Object usernameObj = session.getAttribute("username");
                if (usernameObj != null) {
                    String username = (String) usernameObj;
                    userRepo.findByUsername(username).ifPresent(user -> {
                        user.setLoggedIn(0);
                        userRepo.save(user);
                        System.out.println("User '" + username + "' logged_in set to 0 due to session expiration.");
                    });
                }
                session.invalidate();
            }
            System.out.println("Session expired or user not logged in. Redirecting to login page. Attempted URI: " + uri);
            res.sendRedirect(req.getContextPath() + "/?sessionExpired=true");
            return;
        }

        chain.doFilter(request, response);
    }
}
