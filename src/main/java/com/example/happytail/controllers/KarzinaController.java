package com.example.happytail.controllers;

import com.example.happytail.models.Karzina;
import com.example.happytail.models.User;
import com.example.happytail.repo.KarzinaRepository;
import com.example.happytail.repo.UserRepository;
import com.example.happytail.service.KarzinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/karzina")
public class KarzinaController {

    private final KarzinaService karzinaService;
    private UserRepository userRepository;
    private KarzinaRepository karzinaRepository;

    public KarzinaController(KarzinaService karzinaService) {
        this.karzinaService = karzinaService;
    }


    @GetMapping("/user/{userId}")
    public List<Karzina> getKarzina(@PathVariable Long userId) {
        return karzinaService.getKarzinaByUser(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToKarzina(@RequestBody Map<String, Object> body) {
        try {
            Long userId = Long.valueOf(body.get("userId").toString());
            String name = body.get("name").toString();
            String itemImg = body.get("itemImg").toString();
            double price = Double.parseDouble(body.get("price").toString());
            int count = Integer.parseInt(body.get("count").toString());

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            Karzina k = new Karzina();
            k.setUser(user);
            k.setName(name);
            k.setItemImg(itemImg);
            k.setPrice(price);
            k.setCount(count);

            karzinaRepository.save(k);
            return ResponseEntity.ok("Item added to karzina");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка: " + e.getMessage());
        }
    }

}
