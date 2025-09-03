package com.example.happytail.controllers;

import com.example.happytail.models.Shop_Item;
import com.example.happytail.models.User;
import com.example.happytail.repo.Shop_ItemRepository;
import com.example.happytail.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class Shop_ItemController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Shop_ItemRepository shopItemRepository;

    @GetMapping("/shop/holistics")
    public String showholstics(Model model) {
        List<Shop_Item> items = shopItemRepository.findAll();
        model.addAttribute("items", items);
        return "holistics"; // имя шаблона без .html
    }

    @GetMapping("/{userId}/shop/holistics")
    public String shopHolistics(@PathVariable Long userId, Model model) {
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId не может быть пустым");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/error";
        }

        List<Shop_Item> items = shopItemRepository.findAll();

        model.addAttribute("items", items);
        model.addAttribute("isRegistered", true);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("nickName", user.getNick_name());
        model.addAttribute("userId", user.getId());
        model.addAttribute("userAva", user.getUser_ava());
        model.addAttribute("title", "HappyTail");

        return "holistics_reg"; // шаблон holistics_reg.html
    }

}
