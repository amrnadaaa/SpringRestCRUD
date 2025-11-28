package com.example.springRest.service;

import com.example.springRest.dto.ProductDetailsDTO;
import com.example.springRest.entity.Product;
import com.example.springRest.entity.ProductDetails;
import com.example.springRest.exception.ProductNotFoundException;
import com.example.springRest.mapper.productDetailsMapper;
import com.example.springRest.repository.productDetailsRepository;
import com.example.springRest.repository.productRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class productDetailsService {

    private final productDetailsRepository productDetailsRepository;
    private final productDetailsMapper productDetailsMapper;
    private final productRepository productRepository;


    public productDetailsService(productDetailsRepository productDetailsRepository, productDetailsMapper productDetailsMapper, productRepository productRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.productDetailsMapper = productDetailsMapper;
        this.productRepository = productRepository;
    }


    public ProductDetailsDTO addProductDetails(ProductDetails productDetails) {

        return productDetailsMapper.toDTO(productDetailsRepository.save(productDetails));
    }

    public ProductDetailsDTO updateProductDetails(ProductDetails productDetails, Long id) throws ProductNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("the product not found"));

        ProductDetails productDetails1 = productDetailsRepository.findByProduct(product);
        productDetailsMapper.updateFromRequest(productDetails, productDetails1);
        return productDetailsMapper.toDTO(productDetailsRepository.save(productDetails));

    }

    public void deleteProductDetails(Long id) throws ProductNotFoundException {

        // there is another way to delete?
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("the product not found"));
        ProductDetails productDetailsToDelete = product.getProductDetails();
        if (productDetailsToDelete == null) throw new ProductNotFoundException("the productDetails not found");
        product.setProductDetails(null);
        productRepository.save(product);
        productDetailsRepository.delete(productDetailsToDelete);
    }

    public ProductDetailsDTO updateProductDetailsPartial(ProductDetails productDetails, Long id) throws ProductNotFoundException {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("the product not found"));

        ProductDetails productDetails1 = existing.getProductDetails();
        productDetailsMapper.updateFromRequest(productDetails, productDetails1);
        return productDetailsMapper.toDTO(productDetailsRepository.save(productDetails));

    }

}
