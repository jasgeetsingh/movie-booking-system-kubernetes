package com.mbs.adminservice.controller;


import com.mbs.adminservice.model.User;
import com.mbs.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public User addNewUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }
}
