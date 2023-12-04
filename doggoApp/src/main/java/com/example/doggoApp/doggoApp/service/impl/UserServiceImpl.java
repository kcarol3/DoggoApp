package com.example.doggoApp.doggoApp.service.impl;

import com.example.doggoApp.doggoApp.domain.User;
import com.example.doggoApp.doggoApp.repository.UserRepository;
import com.example.doggoApp.doggoApp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);

        if (user != null && !user.getIsDeleted()) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User getUserById(Long Id) {
        User user = userRepository.findById(Id).get();

        return user;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long Id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(Id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            if (!existingUser.getIsDeleted()) {
                existingUser.setName(userDetails.getName());
                existingUser.setSurname(userDetails.getSurname());
                existingUser.setUsername(userDetails.getUsername());
                existingUser.setEmail(userDetails.getEmail());
                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));

                return userRepository.save(existingUser);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long Id) {
        Optional<User> userOptional = userRepository.findById(Id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setIsDeleted(true);
            userRepository.save(existingUser);
        }
    }
}
