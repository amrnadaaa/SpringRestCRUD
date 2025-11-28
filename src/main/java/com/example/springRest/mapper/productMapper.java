package com.example.springRest.mapper;

import com.example.springRest.dto.ProductDTO;

import com.example.springRest.entity.Product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface productMapper {
    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product entity);
    List<ProductDTO> toDTOs(List<Product> entityList);

}
