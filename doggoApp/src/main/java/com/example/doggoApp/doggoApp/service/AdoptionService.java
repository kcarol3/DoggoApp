package com.example.doggoApp.doggoApp.service;

import com.example.doggoApp.doggoApp.domain.Adoption;
import com.example.doggoApp.doggoApp.domain.Status;

import java.util.List;

public interface AdoptionService {
    public void startAdoption(Adoption adoption);
    public void cancelAdoption(Long adoptionId);
    public void updateStatus(Long adoptionId, Status newStatus);
    public List<Adoption> getAdoptionsByUserId(Long userId);

    public Adoption getAdoptionById(Long adoptionId);
}
