package com.example.doggoApp.doggoApp.repository;

import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.domain.Announcement;
import com.example.doggoApp.doggoApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> getByUser(User user);
}
