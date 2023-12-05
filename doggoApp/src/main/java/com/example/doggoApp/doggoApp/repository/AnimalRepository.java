package com.example.doggoApp.doggoApp.repository;

import com.example.doggoApp.doggoApp.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAnimalsByUserId(Long userId);

}
