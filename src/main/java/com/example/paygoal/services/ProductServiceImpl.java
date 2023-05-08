package com.example.paygoal.services;

import com.example.paygoal.dtos.MessageDto;
import com.example.paygoal.entities.Product;
import com.example.paygoal.exceptions.GenericException;
import com.example.paygoal.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findByOrderByPrice() {
        return productRepository.findByOrderByPrice();
    }

    @Override
    public Product findByIdOrName(Long id, String name) {
        return productRepository.findByIdOrName(id, name).orElseThrow(() -> { throw new NoSuchElementException("No ha sido posible encontrar el producto."); });
    }

    @Override
    public Product save(Product product) {
        if (productRepository.existsProductByName(product.getName())){
            throw new GenericException("Ese nombre ya existe.");
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, Long id) {
        Product editProduct = productRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("No puede editarse porque el producto no existe.");
        });
        editProduct.setName(product.getName());
        editProduct.setDescription(product.getDescription());
        editProduct.setPrice(product.getPrice());
        editProduct.setQuantity(product.getQuantity());
        return editProduct;
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
