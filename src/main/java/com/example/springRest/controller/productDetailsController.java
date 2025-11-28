package com.example.springRest.controller;

import com.example.springRest.dto.ProductDetailsDTO;
import com.example.springRest.entity.ProductDetails;
import com.example.springRest.exception.ProductNotFoundException;
import com.example.springRest.service.productDetailsService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class productDetailsController {
    private final productDetailsService productDetailsService;

    public productDetailsController(productDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    @PostMapping("/productDetails")
    public ProductDetailsDTO addProductDetails(@RequestBody ProductDetails productDetails) {

        return productDetailsService.addProductDetails(productDetails);

    }

    @PutMapping("/productDetails/{id}")
    public ProductDetailsDTO updateProductDetails
            (@RequestBody ProductDetails productDetails, @PathVariable Long id) throws ProductNotFoundException {
        return productDetailsService.updateProductDetails(productDetails, id);
    }


    @PatchMapping("/productDetailsPartial/{id}")
    public ProductDetailsDTO updateProductDetailsPartial
            (@RequestBody ProductDetails productDetails, @PathVariable Long id) throws ProductNotFoundException {
        return productDetailsService.updateProductDetailsPartial(productDetails, id);
    }


    @DeleteMapping("/productDetails/{id}")
    public void deleteProductDetails(@PathVariable Long id) throws ProductNotFoundException {
        productDetailsService.deleteProductDetails(id);
    }


}
