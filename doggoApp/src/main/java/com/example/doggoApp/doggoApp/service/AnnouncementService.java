package com.example.doggoApp.doggoApp.service;

import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.domain.Announcement;
import com.example.doggoApp.doggoApp.domain.Dog;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface AnnouncementService {
    Announcement getById(Long Id);
    List<Announcement> getAllAnnouncements();
    Announcement create(Announcement announcement);
    Announcement update(Long Id, Announcement announcement);
    void delete(Announcement announcement);
}
