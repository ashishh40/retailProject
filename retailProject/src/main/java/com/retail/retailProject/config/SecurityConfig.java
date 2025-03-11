package com.retail.retailProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http
        //     .csrf().disable()
        //     .authorizeHttpRequests(auth -> auth
        //         .requestMatchers("/api/public/**").permitAll()  // Public endpoints
        //         .requestMatchers("/api/user/**").authenticated()  // User-specific endpoints
        //         .requestMatchers("/api/admin/**").hasRole("ADMIN")  // Admin access only
        //         .anyRequest().authenticated()  // Default: require authentication
        //     )
        //     .formLogin().disable()
        //     .httpBasic();  // Enable basic authentication for simplicity
    //     http
    // .csrf().disable()  
    // .authorizeRequests()
    //     .requestMatchers("/api/user","/api/register", "/api/users/**").permitAll()  
    //     .anyRequest().permitAll()  
    // .and()
    // .formLogin().disable()  
    // .httpBasic().disable();
    http
    .csrf().disable()  
    .authorizeRequests()
        .requestMatchers("/api/register","/api/items","/api/login/user", "/api/user", "/api/users/**").permitAll()  
        .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
