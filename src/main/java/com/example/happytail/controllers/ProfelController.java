package com.example.happytail.controllers;

import com.example.happytail.models.User;
import com.example.happytail.repo.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfelController {

    @Autowired
    private UserRepository userRepository;

    // Метод для отображения корзины
    @GetMapping("/profile/cart")
    public String viewCart(HttpSession session, Model model) {
//        List<CartItem> cart = (List<CartItem>) session.getAttribute("cartItems");
//        if (cart == null) cart = new ArrayList<>();

//        double total = cart.stream()
//                .mapToDouble(item -> item.getPrice() * item.getQuantity())
//                .sum();
//
//        model.addAttribute("cartItems", cart);
//        model.addAttribute("total", total);

        User user = (User) session.getAttribute("user");
        if (user != null) {
            populateUserModel(user, model);
        }

        return "profile"; // Или "cart.html", если есть отдельный шаблон
    }

    // Профиль пользователя
    @GetMapping("/{userId}/profile")
    public String profile(@PathVariable Long userId, Model model) {
        User user = getUserOrThrow(userId);
        populateUserModel(user, model);
        return "profile_reg";
    }

    // Страница магазина
    @GetMapping("/{userId}/shop")
    public String shop(@PathVariable Long userId, Model model) {
        User user = getUserOrThrow(userId);
        populateUserModel(user, model);
        return "shop_reg";
    }

    // Страница ветеринарии
    @GetMapping("/{userId}/medic")
    public String medic(@PathVariable Long userId, Model model) {
        User user = getUserOrThrow(userId);
        populateUserModel(user, model);
        return "medic_reg";
    }

    // Страница распродажи
    @GetMapping("/{userId}/sale")
    public String sale(@PathVariable Long userId, Model model) {
        User user = getUserOrThrow(userId);
        populateUserModel(user, model);
        return "sale_reg"; // Исправлено с medic_reg на sale_reg
    }

    // Метод для получения пользователя или исключения
    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
    }

    // Общий метод для добавления данных пользователя в модель
    private void populateUserModel(User user, Model model) {
        model.addAttribute("isRegistered", true);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("nickName", user.getNick_name());
        model.addAttribute("userId", user.getId());
        model.addAttribute("userAva", user.getUser_ava());
        model.addAttribute("title", "HappyTail");
    }
}
