package com.mydev.flybox.controller;

import com.mydev.flybox.errors.BadRequestException;
import com.mydev.flybox.model.User;
import com.mydev.flybox.service.AuthService;
import com.mydev.flybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


// We do not use api pathway here! Because those pathways require authentication...

@RestController
@CrossOrigin({"http://localhost:3000"})
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("register")
    public User register(@RequestBody User user) throws BadRequestException {

        if(user == null){
            throw new BadRequestException("An unexpected error occurred. User is null.");
        } else if (userService.getUserByUsername(user.getUsername()) != null
                && userService.getUserByEmail(user.getEmail()) != null) {
            throw new BadRequestException("A user with this username and email already exists.");
        } else if (userService.getUserByUsername(user.getUsername()) != null) {
            throw new BadRequestException("A user with this username already exists.");
        } else if (userService.getUserByEmail(user.getEmail()) != null) {
            throw new BadRequestException("A user with this email already exists.");
        }

        user = authService.register(user);

        return user;

    }


    @GetMapping("authenticate")
    public User authenticate(Principal principal){
        return userService.getUserByUsername(principal.getName());
    }

}
