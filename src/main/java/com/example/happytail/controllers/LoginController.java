package com.example.happytail.controllers;

import com.example.happytail.models.User;
import com.example.happytail.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return "Неверное имя пользователя или пароль";
        }

        return String.valueOf(existingUser.getId()); // возвращаем ID пользователя
    }

}
