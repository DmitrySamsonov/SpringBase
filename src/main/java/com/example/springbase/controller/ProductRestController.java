package com.example.springbase.controller;

import com.example.springbase.model.Product;
import com.example.springbase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @GetMapping(value = "/list")
    public Iterable list(Model model) {
        Iterable productList = productService.listAllProducts();
        return productList;
    }

    @GetMapping(value = "/show/{id}")
    public Product showProduct(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        return product;
    }

    @PostMapping(value = "/add")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product storedProduct = productService.getProductById(id);
        storedProduct.setName(product.getName());
        storedProduct.setPrice(product.getPrice());
        productService.addProduct(storedProduct);
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
    }
}
