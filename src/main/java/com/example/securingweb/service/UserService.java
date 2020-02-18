package com.example.securingweb.service;

import com.example.securingweb.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);

    User findByEmail(String email);
}
