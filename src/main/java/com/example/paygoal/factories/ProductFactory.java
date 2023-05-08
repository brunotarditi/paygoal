package com.example.paygoal.factories;

import com.example.paygoal.dtos.ProductDto;
import com.example.paygoal.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory implements FactoryConvert<Product, ProductDto> {
    @Override
    public Product createEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    @Override
    public ProductDto createDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        return productDto;
    }


}
