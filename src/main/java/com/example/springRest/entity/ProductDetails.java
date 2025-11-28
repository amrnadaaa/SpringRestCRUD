package com.example.springRest.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "ProductDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double price;
    private int stock_quantity;
    private Date created_at;
    private Date updated_at;
    private double weight_kg;
    private String country_of_origin;
    @JoinColumn(name = "product_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;


}
