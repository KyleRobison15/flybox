package com.mydev.flybox.service;

import com.mydev.flybox.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers ();
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
