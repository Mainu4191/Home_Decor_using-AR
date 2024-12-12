package com.example.arproject1.filters;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.arproject1.Services.jwt.AdminServiceImpl;
import com.example.arproject1.utils.JwtUtil;

import io.jsonwebtoken.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final AdminServiceImpl adminAuthServicesImpl;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(AdminServiceImpl adminAuthServicesImpl, JwtUtil jwtUtil) {
        this.adminAuthServicesImpl = adminAuthServicesImpl;
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {
                String authHeader = request.getHeader("Authorization");
                String token = null;
                String username = null;
        
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                    username = jwtUtil.extractUsername(token);
                }
        
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = adminAuthServicesImpl.loadUserByUsername(username);
        
                    if (jwtUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
        
                }
        
                try {
                    filterChain.doFilter(request, response);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
        
            }
    
}
