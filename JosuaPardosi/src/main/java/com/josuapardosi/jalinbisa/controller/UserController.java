package com.josuapardosi.jalinbisa.controller;

import com.josuapardosi.jalinbisa.model.User;
import com.josuapardosi.jalinbisa.repository.UserRepository;
import com.josuapardosi.jalinbisa.service.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/new")
    public User addNewUser(@RequestBody User userInfo){
        return userService.addUser(userInfo);
    }


    @DeleteMapping("/deleteUser")
    public String deleteUser(Authentication user){
        return userService.deleteUser(user.getName());
    }

    @GetMapping("/getUser")
    public Optional<User> getUser(Authentication user){
        return userService.getUser(user.getName());
    }

}
