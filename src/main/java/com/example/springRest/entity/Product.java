package com.example.springRest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Table(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "product Name can't be empty")
    private String productName;
    @NotEmpty(message = "product sku can't be empty")
    @Column(unique = true, nullable = false)
    private String sku;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetails productDetails;
}
