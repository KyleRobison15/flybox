package com.mydev.flybox.service;

import com.mydev.flybox.model.Address;
import com.mydev.flybox.model.User;
import com.mydev.flybox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public User register(User user) {

        List<String> roles = new ArrayList<>();
        roles.add("user");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(roles);
        user.setFlies(new ArrayList<>()); // Add an empty array as the user's flies when they register
        user.setAddress(new Address()); // Add null address when they register (they can update this later)
        user.setCreateDate(LocalDate.parse(LocalDate.now().format(formatter)));
        userRepo.save(user);

        return user;
    }
}
