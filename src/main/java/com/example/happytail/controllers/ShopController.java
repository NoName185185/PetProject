package com.example.happytail.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
//    @PostMapping("/add-to-cart")
//    public String addToCart(@RequestParam String name,
//                            @RequestParam String image,
//                            @RequestParam double price,
//                            HttpSession session) {
//
//        List<CartItem> cart = (List<CartItem>) session.getAttribute("cartItems");
//        if (cart == null) {
//            cart = new ArrayList<>();
//        }
//
//        // Проверка: если уже есть такой товар, увеличиваем количество
//        boolean found = false;
//        for (CartItem item : cart) {
//            if (item.getProductName().equals(name)) {
//                item.setQuantity(item.getQuantity() + 1);
//                found = true;
//                break;
//            }
//        }
//
//        if (!found) {
//            cart.add(new CartItem(name, image, price, 1));
//        }
//
//        session.setAttribute("cartItems", cart);
//
//        return "redirect:/shop"; // или куда нужно
//    }

    @GetMapping("/shop")
    public String shoplog(Model model) {
        return "shop";
    }
}
