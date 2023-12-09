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

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getName(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//    }

    public User findById(Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data tidak ada"));
    }

    public User addUser(User userInfo) {
        if(userInfo.getId() !=null)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data Sudah Ada");
        }
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userRepository.save(userInfo);
    }


    public String deleteUser(String user) {
        Optional<User> users = userRepository.findByName((user));
        userRepository.deleteEmp(user);
        return "User deleted";
    }



    public Optional<User> getUser(String name) {
        return userRepository.findByName(name);
    }

    public boolean isCurrentUser( String name) {
        Optional<User> currentUser = getUser(name);
        return true;
    }



}
