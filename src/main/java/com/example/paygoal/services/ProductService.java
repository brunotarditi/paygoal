package com.example.paygoal.services;

import com.example.paygoal.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findByOrderByPrice();
}
