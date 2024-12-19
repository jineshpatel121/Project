
package com.lockedin.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository 
{
    public List<Product> findByName(String name) 
    {
        return products.stream()
            .filter(product -> product.getName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) 
    {
        return products.stream()
            .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
            .collect(Collectors.toList());
    }
    private List<Product> products = new ArrayList<>();

    public List<Product> findAll() 
    {
        return new ArrayList<>(products);
    }

    public void save(Product product) 
    {
        products.add(product);
    }
}
