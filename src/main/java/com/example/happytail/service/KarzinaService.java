package com.example.happytail.service;

import com.example.happytail.models.Karzina;
import com.example.happytail.models.Shop_Item;
import com.example.happytail.models.User;
import com.example.happytail.repo.KarzinaRepository;
import com.example.happytail.repo.Shop_ItemRepository;
import com.example.happytail.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KarzinaService {

    private final KarzinaRepository karzinaRepository;
    private final UserRepository userRepository;
    private final Shop_ItemRepository shopItemRepository;

    public KarzinaService(KarzinaRepository karzinaRepository,
                          UserRepository userRepository,
                          Shop_ItemRepository shopItemRepository) {
        this.karzinaRepository = karzinaRepository;
        this.userRepository = userRepository;
        this.shopItemRepository = shopItemRepository;
    }

    public void addToKarzina(Long userId, Long shopItemId, int count) {
        User user = userRepository.findById(userId).orElseThrow();
        Shop_Item item = shopItemRepository.findById(shopItemId).orElseThrow();

        Karzina karzina = new Karzina();
        karzina.setUser(user);
        karzina.setName(item.getName());
        karzina.setItemImg(item.getItemImg());
        karzina.setPrice(item.getPrice());
        karzina.setCount(count);

        karzinaRepository.save(karzina);
    }

    public List<Karzina> getKarzinaByUser(Long userId) {
        return karzinaRepository.findByUserId(userId);
    }
}
