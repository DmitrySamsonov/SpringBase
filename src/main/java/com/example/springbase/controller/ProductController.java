package com.example.springbase.controller;

import com.example.springbase.model.Product;
import com.example.springbase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
@RequestMapping("/ui/product")
public class ProductController {


    @Autowired
    private ProductService productService;


    @PostMapping
    @RequestMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam(defaultValue = "0") Integer price,
                      Map<String, Object> model) {

        Product product = new Product(name, price);
        productService.addProduct(product);

        //TODO так делать плохо.
        Iterable<Product> productList = productService.listAllProducts();
        model.put("productList", productList);

        return "product/productPage";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable productList = productService.listAllProducts();
        model.put("productList", productList);
        return "product/productPage";
    }


    @GetMapping("/filterByName")
    public String selectByName(@RequestParam String filter,
                               Map<String, Object> model) {

        Iterable<Product> productList;
        if (filter != null && !filter.isEmpty()) {
            productList = productService.getProductsByName(filter);
        } else {
            productList = productService.listAllProducts();
        }
        model.put("productList", productList);
        return "product/productPage";
    }


    @PostMapping
    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id,
                         Map<String, Object> model) {
        productService.deleteProduct(id);

        //TODO так делать плохо.
        Iterable<Product> productList = productService.listAllProducts();
        model.put("productList", productList);

        return "product/productPage";
    }

    @GetMapping
    @RequestMapping("/edit")
    public String edit(@RequestParam Integer id,
                       Map<String, Object> model) {

        Product product = productService.getProductById(id);

        model.put("id", product.getId());
        model.put("name", product.getName());
        model.put("price", product.getPrice());

        return "product/productPageEdit";
    }


    @PostMapping
    @RequestMapping("/edit/save")
    public String editSave(@RequestParam Integer id,
                           @RequestParam String name,
                           @RequestParam Integer price,
                           Map<String, Object> model) {
        Product saveProduct = productService.getProductById(id);
        saveProduct.setName(name);
        saveProduct.setPrice(price);
        productService.addProduct(saveProduct);

        //TODO так делать плохо.
        Iterable<Product> productList = productService.listAllProducts();
        model.put("productList", productList);
        return "product/productPage";
    }


}
