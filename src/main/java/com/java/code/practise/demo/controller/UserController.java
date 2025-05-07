package com.java.code.practise.demo.controller;

import com.java.code.practise.demo.dto.CreateUserResponse;
import com.java.code.practise.demo.entity.User;
import com.java.code.practise.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        CreateUserResponse response = new CreateUserResponse(savedUser.getId());
        return ResponseEntity
                .created(URI.create("/api/v1/users/" + savedUser.getId()))
                .body(response);

    }
}
