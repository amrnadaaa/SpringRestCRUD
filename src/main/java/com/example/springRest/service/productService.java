package com.example.springRest.service;
import com.example.springRest.dto.ProductDTO;
import com.example.springRest.entity.Product;
import com.example.springRest.exception.productExistException;
import com.example.springRest.mapper.productMapper;
import com.example.springRest.repository.productRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class productService {
    private final productRepository productRepository;
    private final productMapper productMapper;

    public productService(productRepository productRepository, productMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO addProduct(Product product) throws productExistException {
        if (productRepository.findBySku(product.getSku()) != null) throw
                new productExistException("product already exists");

        return productMapper.toDTO(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productMapper.toDTOs(productRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(long id) {
        return productMapper.toDTO(productRepository.findById(id).get());
    }
}
