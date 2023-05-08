package com.example.paygoal.services;

import com.example.paygoal.dtos.MessageDto;
import com.example.paygoal.dtos.ProductDto;
import com.example.paygoal.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findByOrderByPrice();

    ProductDto findByIdOrName(Long id, String name);

    Product save(ProductDto productDto);

    Product update(ProductDto productDto, Long id);
    MessageDto delete(Long id);
}
