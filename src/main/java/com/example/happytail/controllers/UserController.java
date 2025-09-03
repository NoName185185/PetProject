package com.example.happytail.controllers;

import com.example.happytail.models.User;
import com.example.happytail.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // DTO для принятия JSON
    public static class AvatarUpdateRequest {
        public String avatarUrl;
    }

    @PostMapping("/{id}/update-avatar")
    public String updateAvatar(@PathVariable Long id, @RequestBody AvatarUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return "Пользователь не найден";
        }

        User user = optionalUser.get();
        user.setUser_ava(request.avatarUrl);
        userRepository.save(user);

        return "OK";
    }
}
