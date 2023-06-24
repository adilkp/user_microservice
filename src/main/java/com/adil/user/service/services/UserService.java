package com.adil.user.service.services;

import com.adil.user.service.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);
}
