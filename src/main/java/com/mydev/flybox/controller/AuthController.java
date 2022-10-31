package com.mydev.flybox.controller;

import com.mydev.flybox.model.User;
import com.mydev.flybox.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

// We do not use api pathway here! Because those pathways require authentication...

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("register")
    public User register(@RequestBody User user, HttpServletResponse res) {

        if(user == null){
            res.setStatus(400);
        }

        user = authService.register(user);

        return user;

    }

    // Test endpoint to verify that we can authenticate a User
    @GetMapping("authenticate")
    public Principal authenticate(Principal principal){
        return principal;
    }

}
