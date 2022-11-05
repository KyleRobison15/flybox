package com.mydev.flybox.controller;

import com.mydev.flybox.model.Fly;
import com.mydev.flybox.model.User;
import com.mydev.flybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:3000/*"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("users/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("fly")
    public User createFly(@RequestBody Fly fly, Principal principal){
        return userService.addFly(fly, principal.getName());
    }

}
