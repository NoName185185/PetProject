package com.example.happytail.controllers;

import com.example.happytail.models.Event;
import com.example.happytail.models.User;

import com.example.happytail.repo.EventRepository;
import com.example.happytail.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String nonmain(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("title", "HappyTail");
        return "main";
    }

//    @GetMapping("/main")
//    public String mainlog(Model model) {
//        Iterable<Event> events = eventRepository.findAll();
//        model.addAttribute("events", events);
//        model.addAttribute("title", "HappyTail");
//        return "main";
//    }

    @GetMapping("/main")
    public String showUnregisteredMainPage(Model model) {
        model.addAttribute("isRegistered", false);
        model.addAttribute("title", "HappyTail");
        model.addAttribute("events", eventRepository.findAll());
        return "main";
    }

    @GetMapping("/{userId}/main")
    public String showMainPage(@PathVariable Long userId, Model model) {
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId не может быть пустым");
        }
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return "redirect:/error"; // или своя страница ошибки
        }

        model.addAttribute("isRegistered", true);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userAva", user.getUser_ava());
        model.addAttribute("userId", user.getId());
        model.addAttribute("title", "HappyTail");
        model.addAttribute("events", eventRepository.findAll());

        return "main";
    }

    @GetMapping("/{userId}/main_registered")
    public String showRegisteredMainPage(@PathVariable Long userId, Model model) {
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId не может быть пустым");
        }
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return "redirect:/error"; // или своя страница ошибки
        }

        model.addAttribute("isRegistered", true);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("nickName", user.getNick_name());
        model.addAttribute("userId", user.getId());
        model.addAttribute("userAva", user.getUser_ava());
        model.addAttribute("title", "HappyTail");
        model.addAttribute("events", eventRepository.findAll());

        return "main_registered";
    }


}
