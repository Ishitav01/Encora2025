package com.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Allow static resources and login/register and root
        boolean allowed = uri.equals("/") || uri.equals("/login") || uri.equals("/register") ||
                uri.startsWith(req.getContextPath() + "/css") || uri.startsWith(req.getContextPath() + "/js") ||
                uri.startsWith(req.getContextPath() + "/images") || uri.contains("/static/");

        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("username") != null);

        if (allowed || loggedIn) {
            chain.doFilter(request, response);
        } else {
            // redirect to login (root) - use context path
            res.sendRedirect(req.getContextPath() + "/?sessionExpired=true");
        }
    }
}