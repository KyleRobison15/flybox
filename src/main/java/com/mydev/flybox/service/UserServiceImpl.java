package com.mydev.flybox.service;

import com.mydev.flybox.model.Fly;
import com.mydev.flybox.model.User;
import com.mydev.flybox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    ////////////////////////////////////////////// REPOSITORIES //////////////////////////////////////////////////////
    @Autowired
    private UserRepository userRepository;

    ////////////////////////////////////////////// SERVICE METHODS //////////////////////////////////////////////////////
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addFly(Fly fly, String username) {

        User user = userRepository.findByUsername(username);
        user.getFlies().add(fly);

        return userRepository.save(user);
    }

    ////////////////////////////////////////////// SPRING SECURITY UserDetailsService Implementation //////////////////////////////////////////////////////
    // This method is overriding the loadUserByUsername method in the UserDetailsService interface
    // We need this method to tell Spring how to load a user from our database, so we can perform authentication using Spring Security
    // The method must return a User object with a username, password, and List of authorities.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> userRoles;
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Use our existing user repository to find a user in our DB by username
        User user = userRepository.findByUsername(username);

        // If the user was not found, throw an exception with a message
        if(user == null){
            throw new UsernameNotFoundException("This username does not exist");
        }

        // Next we must iterate over the list of roles for our user and create a new List<SimpleGrantedAuthorities>
        // Spring will use this list to manage the permissions each user has
        userRoles = user.getRoles();

        for (String role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
