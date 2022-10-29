package com.mydev.flybox.service;

import com.mydev.flybox.model.User;
import com.mydev.flybox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        user.setCreateDate(LocalDate.parse(LocalDate.now().format(formatter)));
        return userRepository.save(user);
    }

//    @Override
//    public User updateUser(String username, User user) {
//        return null;
//    }


}
