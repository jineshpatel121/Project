package com.lockedin.myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Bean for password encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",                // Root route
                    "/products",        // Public home page (product listing)
                    "/signup",          // Signup page
                    "/forgot-password", // Forgot password page
                    "/reset-password",  // Reset password page
                    "/h2-console/**",   // H2 database console
                    "/styles.css",      // CSS files
                    "/homeScript.js"    // JavaScript files
                ).permitAll() // Publicly accessible routes
                .anyRequest().authenticated() // All other routes require authentication
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // Disable CSRF for H2 Console
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable()) // Allow H2 Console to load in frames
            )
            .formLogin(form -> form
                .loginPage("/") // Custom login page
                .defaultSuccessUrl("/products", true) // Redirect to /products after successful login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/").permitAll() // Redirect to root on logout
            );

        return http.build();
    }
}
