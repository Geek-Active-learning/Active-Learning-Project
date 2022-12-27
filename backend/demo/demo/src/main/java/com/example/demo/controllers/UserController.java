package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId){
        return this.userService.getUserById(userId);
    }

    @PostMapping
    public void createNewUser(@RequestBody User user){
        this.userService.createNewUser(user);
    }

    @PutMapping
    public Optional<User> updateUser(@RequestBody User user, @PathVariable Long id){
        return this.userService.updateUser(user,id);
    }

    @DeleteMapping
    public void deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
    }
}
