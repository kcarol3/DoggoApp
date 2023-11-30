package com.example.doggoApp.doggoApp.repository;

import com.example.doggoApp.doggoApp.domain.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    List<Adoption> findAdoptionsByUserId(Long userId);
}
