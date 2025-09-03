package com.example.happytail.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicController {
    @GetMapping("/medic")
    public String mediclog(Model model) {
        return "medic";
    }
}
