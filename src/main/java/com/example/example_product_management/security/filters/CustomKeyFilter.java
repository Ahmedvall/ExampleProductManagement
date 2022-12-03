package com.example.example_product_management.security.filters;

import com.example.example_product_management.security.authentication.CustomAuthentication;
import com.example.example_product_management.security.manager.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomKeyFilter extends OncePerRequestFilter {
    private final String key;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var manager = new CustomAuthenticationManager(key);

        var requestKey = request.getHeader("app-key");
        if ("null".equals(requestKey) || requestKey == null) {
            filterChain.doFilter(request, response);
        }

        var auth = new CustomAuthentication(requestKey);

        try {
            var a = manager.authenticate(auth);
            if (a.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
