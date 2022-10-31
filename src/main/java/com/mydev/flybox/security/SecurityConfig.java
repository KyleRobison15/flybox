package com.mydev.flybox.security;

import com.mydev.flybox.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // We need to declare a UserDetailsService Bean that returns whatever we want to use as our UserDetailsService
    // Now, when a request is made according to the config we have in our SecurityFilterChain, Spring will inject our an instance of our UserServiceImpl class
    // Since our User Service Impl implements a UserDetailsService, Spring will know how to load and authenticate a user from our Mongo DB
    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    // SecurityFilterChain bean is how Spring knows which paths in our API need to be authenticated
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
                .antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
                .anyRequest().permitAll()               // All other requests are allowed without authorization.
                .and()
                .httpBasic() // Use HTTP Basic Authentication
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

}
