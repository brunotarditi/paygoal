package com.example.paygoal.services;

import com.example.paygoal.dtos.MessageDto;
import com.example.paygoal.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findByOrderByPrice();

    Product findByIdOrName(Long id, String name);

    Product save(Product product);

    Product update(Product product, Long id);
    MessageDto delete(Long id);
}
