package com.example.springRest.controller;

import com.example.springRest.dto.ProductDTO;
import com.example.springRest.entity.Product;
import com.example.springRest.exception.productExistException;
import com.example.springRest.service.productService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {
    private final productService productService;

    public productController(productService productService) {
        this.productService = productService;
    }


    @PostMapping("/product")
    public ProductDTO addProduct(@RequestBody Product product) throws productExistException {
        return productService.addProduct(product);

    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }
}
