package com.example.springRest.repository;

import com.example.springRest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product,Long> {
    public Product findBySku(String sku);

}
