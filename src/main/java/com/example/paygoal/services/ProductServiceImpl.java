package com.example.paygoal.services;

import com.example.paygoal.dtos.MessageDto;
import com.example.paygoal.dtos.ProductDto;
import com.example.paygoal.entities.Product;
import com.example.paygoal.exceptions.GenericException;
import com.example.paygoal.factories.FactoryConvert;
import com.example.paygoal.factories.ProductFactory;
import com.example.paygoal.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }


    @Override
    public List<ProductDto> findByOrderByPrice() {
        List<Product> products = productRepository.findByOrderByPrice();
        return products.stream()
                .map(productFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findByIdOrName(Long id, String name) {
        Product product = productRepository.findByIdOrName(id, name).orElseThrow(() -> { throw new NoSuchElementException("No ha sido posible encontrar el producto."); });
        return productFactory.createDto(product);
    }

    @Override
    public Product save(ProductDto productDto) {
        Product product = productFactory.createEntity(productDto);
        if (productRepository.existsProductByName(product.getName())){
            throw new GenericException("Ese nombre ya existe.");
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(ProductDto productDto, Long id) {
        Product editProduct = productRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("No puede editarse porque el producto no existe.");
        });
        editProduct.setName(productDto.getName());
        editProduct.setDescription(productDto.getDescription());
        editProduct.setPrice(productDto.getPrice());
        editProduct.setQuantity(productDto.getQuantity());
        return productRepository.save(editProduct);
    }
    @Override
    public MessageDto delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
            return new MessageDto("El producto se ha eliminado.");
        }
        return new MessageDto("No puede eliminarse porque el producto no existe.");
    }
}
