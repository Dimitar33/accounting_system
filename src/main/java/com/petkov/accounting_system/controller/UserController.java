package com.petkov.accounting_system.controller;

import com.petkov.accounting_system.model.User;
import com.petkov.accounting_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }
}
