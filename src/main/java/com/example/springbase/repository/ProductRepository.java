package com.example.springbase.repository;

import com.example.springbase.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(Integer id);

    Iterable<Product> findByNameContaining(String filter);
}
