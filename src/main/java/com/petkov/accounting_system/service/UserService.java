package com.petkov.accounting_system.service;

import com.petkov.accounting_system.model.User;
import com.petkov.accounting_system.repository.UserRepo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo repository;

    public UserService(UserRepo repo){
        this.repository = repo;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User createUser(User user){

        return repository.save(user);
    }

    public User findByUsername(String username){
        return repository.findByUsername(username).orElse(null);
    }
}
