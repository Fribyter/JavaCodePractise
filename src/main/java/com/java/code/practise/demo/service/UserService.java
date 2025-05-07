package com.java.code.practise.demo.service;

import com.java.code.practise.demo.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User save(User user);
    User findById(Long id);
}
