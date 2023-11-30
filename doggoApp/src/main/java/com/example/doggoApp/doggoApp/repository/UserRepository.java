package com.example.doggoApp.doggoApp.repository;

import com.example.doggoApp.doggoApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserByUsername(String username);
}
