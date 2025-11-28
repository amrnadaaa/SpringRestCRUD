package com.example.springRest.dto;

import lombok.NoArgsConstructor;


public record ProductDetailsDTO(
        String description,
        Double price,
        Double weight_kg,
        String country_of_origin
) {

}

