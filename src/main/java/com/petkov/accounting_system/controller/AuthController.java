package com.petkov.accounting_system.controller;

import com.petkov.accounting_system.dto.AuthResponse;
import com.petkov.accounting_system.dto.LoginRequest;
import com.petkov.accounting_system.dto.RegisterRequest;
import com.petkov.accounting_system.model.User;
import com.petkov.accounting_system.security.JwtUtil;
import com.petkov.accounting_system.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        User existing = userService.findByUsername(request.getUsername());

        if (existing != null) return "This user already exists";

        User user = new User(null, request.getUsername(),passwordEncoder.encode(request.getPassword()), request.getRole());
        userService.createUser(user);
        System.out.println("Raw password: " + request.getPassword());
        System.out.println("Stored hash: " + user.getPassword());
        System.out.println("Matches? " + passwordEncoder.matches(request.getPassword(), user.getPassword()));

        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){

        User user = userService.findByUsername(request.getUsername());

        System.out.println("Raw password: " + request.getPassword());
        System.out.println("Stored hash: " + user.getPassword());
        System.out.println("Matches? " + passwordEncoder.matches(request.getPassword(), user.getPassword()));


        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
