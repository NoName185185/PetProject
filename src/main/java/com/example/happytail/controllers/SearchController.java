package com.example.happytail.controllers;

import com.example.happytail.models.Item;
import com.example.happytail.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam String query) {
        return itemRepository.findByNameContainingIgnoreCase(query);
    }
}
