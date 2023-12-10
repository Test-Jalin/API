package com.josuapardosi.jalinbisa.controller;

import com.josuapardosi.jalinbisa.model.User;
import com.josuapardosi.jalinbisa.repository.UserRepository;
import com.josuapardosi.jalinbisa.service.UserService;
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

    @GetMapping("/welcome")
    public String welcome(){
        return "WELCOME";
    }

    @PostMapping("/new")
    public User addNewUser(@RequestBody User userInfo){
        return userService.addUser(userInfo);
    }


    @DeleteMapping("/deleteUser")
    public String deleteUser(Authentication user){
        return userService.deleteUser(user.getName());
    }

    @GetMapping("/getUser")
    public User getUser(Authentication user){
        return userService.getUser(user.getName());
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User users,Authentication user){
        return userService.update(users,user.getName());
    }

}
