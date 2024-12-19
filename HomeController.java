package com.lockedin.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    // Handles the product list, search, and price filtering
    @GetMapping("/products")
    public String homePage(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Model model) {

        List<Product> products;

        // Handle search and filtering logic
        if (search != null && !search.isEmpty()) {
            products = productRepository.findByName(search);
        } else if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPriceRange(minPrice, maxPrice);
        } else {
            products = productRepository.findAll();
        }

        // Add the product list to the model
        model.addAttribute("products", products);
        return "home"; // Loads the home.html template
    }
}
