package com.mydev.flybox.service;

import com.mydev.flybox.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers ();
    User getUserByUsername(String username);
    User createUser (User user);
//    User updateUser (String username, User user);
}
