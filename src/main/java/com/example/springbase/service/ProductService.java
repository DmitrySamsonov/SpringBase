package com.example.springbase.service;

import com.example.springbase.model.Product;
import com.example.springbase.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Iterable listAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        return products;
    }

    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id);
        return product;
    }

    public Iterable<Product> getProductsByName(String filter) {
        Iterable<Product> products = productRepository.findByNameContaining(filter);
        return products;
    }


    public void deleteProduct(Integer id) {
        Product findedProduct = productRepository.findById(id);
        if (findedProduct != null) {
            productRepository.delete(findedProduct);
        } else {
            System.out.println("Product with id = " + id + " not founded");
        }
    }


}
