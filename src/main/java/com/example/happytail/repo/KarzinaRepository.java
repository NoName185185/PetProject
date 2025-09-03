package com.example.happytail.repo;

import com.example.happytail.models.Karzina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KarzinaRepository extends JpaRepository<Karzina, Long> {
    List<Karzina> findByUserId(Long userId);
}