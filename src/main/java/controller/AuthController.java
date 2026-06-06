package com.ws101.luna.EcommerceApiLab9.controller;

import com.ws101.luna.EcommerceApiLab9.model.User;
import com.ws101.luna.EcommerceApiLab9.model.UserRepository;
import com.ws101.luna.EcommerceApiLab9.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            return Map.of("token", token);
        }
        throw new RuntimeException("Invalid credentials");
    }
}