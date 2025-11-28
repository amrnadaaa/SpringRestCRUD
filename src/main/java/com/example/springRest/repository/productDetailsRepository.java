package com.example.springRest.repository;

import com.example.springRest.entity.Product;
import com.example.springRest.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface productDetailsRepository extends JpaRepository<ProductDetails, Long> {
    ProductDetails findByProduct(Product product);

}
