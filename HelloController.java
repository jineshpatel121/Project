package com.lockedin.myapp;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController 
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection for dependencies
    
    public HelloController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String main() {
        return "main"; // Loads templates/main.html
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // Loads templates/signup.html
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Loads templates/home.html
    }
    

    @PostMapping("/signup")
    public String processSignup(@RequestParam String email,
                                @RequestParam String password,
                                Model model) {
        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email is already registered!");
            return "main";
        }

        // Create a new user and encrypt the password
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt password

        userRepository.save(user);
        model.addAttribute("message", "User registered successfully!");
        return "home"; // Go back to main page to log in
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }
}
