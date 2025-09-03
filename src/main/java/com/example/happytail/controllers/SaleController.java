package com.example.happytail.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaleController {
    @GetMapping("/sale")
    public String salelog(Model model) {
        return "sale";
    }
}
