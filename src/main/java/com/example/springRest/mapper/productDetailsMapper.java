package com.example.springRest.mapper;

import com.example.springRest.dto.ProductDetailsDTO;

import com.example.springRest.entity.Product;
import com.example.springRest.entity.ProductDetails;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface productDetailsMapper {

    ProductDetails toEntity(ProductDetailsDTO dto);

    ProductDetailsDTO toDTO(ProductDetails entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(ProductDetails source, @MappingTarget ProductDetails target);


  //  void updateFromRequest(ProductDetails productDetails,@MappingTarget Optional<ProductDetails> productDetails1);
}
