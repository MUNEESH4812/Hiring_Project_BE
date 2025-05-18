package com.example.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.onboarding.entity.User;
import com.example.onboarding.repository.UserRepository;
import com.example.onboarding.security.ApplicationUserDetails;
import com.example.onboarding.security.ApplicationUserDetailsService;
import com.example.onboarding.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;
    @Autowired private ApplicationUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        ApplicationUserDetails details = (ApplicationUserDetails) userDetailsService.loadUserByUsername(user.getUsername());

        if (!passwordEncoder.matches(user.getPassword(), details.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}
