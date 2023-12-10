package com.josuapardosi.jalinbisa.service;

import com.josuapardosi.jalinbisa.model.User;
import com.josuapardosi.jalinbisa.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User addUser(User userInfo) {
        if(userInfo.getId() !=null)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data Sudah Ada");
        }
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userRepository.save(userInfo);
    }


    public String deleteUser(String user) {
        User users = userRepository.findByName(user);
        userRepository.deleteUser(user);
        return "User deleted";
    }



    public User getUser(String name) {
        return userRepository.findByName(name);
    }



    public User update(User  user, String getUser)
    {
        User users = userRepository.findByName(getUser);
        users.setName(user.getName());
        User updatedUser = userRepository.save(users);
        return updatedUser;

    }

}
