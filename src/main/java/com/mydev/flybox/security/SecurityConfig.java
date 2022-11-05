package com.mydev.flybox.security;

import com.mydev.flybox.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Autowire an instance of our UserServiceImpl so we can pass it in to our Auth Provider
    @Autowired
    private UserServiceImpl userDetailsService;

    // Autowire an instance of our PasswordEncoder so we can pass it in to our Auth Provider
    @Autowired
    private PasswordEncoder encoder;

    // SecurityFilterChain bean is how Spring knows which paths in our API need to be authenticated
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors(Customizer.withDefaults()) // for setting cors
                .authorizeRequests()
                .antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
                .anyRequest().permitAll()               // All other requests are allowed without authorization.
                .and()
                .httpBasic() // Use HTTP Basic Authentication
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

    // Create a new type of Authentication Provider for Spring Security to use when authenticating requests to our API
    // Here we are using a DaoAuthenticationProvider
    // In the future we could define multiple Auth Providers that can authenticate in different ways such as JSON Web Tokens, OAuth, SSO etc.
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
        configuration.addAllowedHeader("authorization");
        configuration.addAllowedHeader("content-type");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
