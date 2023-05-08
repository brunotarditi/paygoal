package com.example.paygoal.repositories;

import com.example.paygoal.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByOrderByPrice();
    Optional<Product> findByIdOrName(Long id, String name);
    boolean existsProductByName(String name);
}
