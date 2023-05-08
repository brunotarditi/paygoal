package com.example.paygoal.controllers;

import com.example.paygoal.entities.Product;
import com.example.paygoal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(productService.findByOrderByPrice(), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getByIdOrName(@RequestParam(required = false) Long id, @RequestParam(required = false) String name){
        return new ResponseEntity<>(productService.findByIdOrName(id, name), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()){
            return validate(result);
        }
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody Product product,  BindingResult result){
        if (result.hasErrors()){
            return validate(result);
        }
        return new ResponseEntity<>(productService.update(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

    public ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
