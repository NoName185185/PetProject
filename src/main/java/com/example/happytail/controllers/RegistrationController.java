package com.example.happytail.controllers;

import com.example.happytail.models.User;
import com.example.happytail.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Атыңыз бен құпия сөз міндетті."));
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Бұл қолданушы аты бұрыннан тіркелген."));
        }

        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "message", "Тіркелу сәтті аяқталды!",
                "userId", savedUser.getId()
        ));
    }


}
