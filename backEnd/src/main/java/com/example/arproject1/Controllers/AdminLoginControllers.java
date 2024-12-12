package com.example.arproject1.Controllers;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arproject1.Model.LoginRequest;
import com.example.arproject1.Model.LoginResponse;
import com.example.arproject1.Services.jwt.AdminServiceImpl;
import com.example.arproject1.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin/login")
public class AdminLoginControllers {

    private final AuthenticationManager authenticationManager;
    private final AdminServiceImpl adminAuthServicesImpl;
    private final JwtUtil jwtUtil;

    public AdminLoginControllers(AuthenticationManager authenticationManager, AdminServiceImpl adminAuthServicesImpl,
            JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.adminAuthServicesImpl = adminAuthServicesImpl;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping()
    public LoginResponse loginAdmin(@RequestBody LoginRequest loginRequest, HttpServletResponse response)
            throws IOException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        } catch (DisabledException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Admin is not activated");
            return null;
        }

        final UserDetails userDetails = adminAuthServicesImpl.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        final String status = "Success";

        return new LoginResponse(jwt, status);
    }
}
