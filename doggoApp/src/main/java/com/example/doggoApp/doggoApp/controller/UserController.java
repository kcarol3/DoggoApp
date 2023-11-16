package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.User;
import com.example.doggoApp.doggoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{Id}")
    public  ResponseEntity<User> getUserById(@PathVariable Long Id){
        User user = userService.getUserById(Id);

        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{Id}")
    public ResponseEntity<User> updateUser(@PathVariable Long Id, @RequestBody User userDetails){
        User updatedUser = userService.updateUser(Id, userDetails);

        if (updatedUser != null){
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long Id){
        userService.deleteUser(Id);

        return new ResponseEntity<>("Success deleted", HttpStatus.OK);
    }

}
