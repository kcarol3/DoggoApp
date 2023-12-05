package com.example.doggoApp.doggoApp.service;

import com.example.doggoApp.doggoApp.domain.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {
    User getUserByUsername(String username);

    User getUserById(Long Id);

    User createUser(User user) throws SQLIntegrityConstraintViolationException;

    User updateUser(Long Id, User userDetails);

    void deleteUser(Long Id);
}
