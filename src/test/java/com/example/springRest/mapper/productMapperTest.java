package com.example.springRest.mapper;

import com.example.springRest.dto.ProductDTO;
import com.example.springRest.entity.Product;
import com.example.springRest.entity.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class productMapperTest {
     private productMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new productMapperImpl();
    }
    @Test
    void shouldMapProductToProductDto ()
    {
        //Given
        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(1L);
        productDetails.setDescription("pure milk");
        productDetails.setPrice(25.5);
        productDetails.setWeight_kg(.5);
        productDetails.setCountry_of_origin("Egypt");
        Product product = new Product(1L,"milk","a222a",productDetails);
        productDetails.setProduct(product);
        //when
        ProductDTO productDTO = productMapper.toDTO(product);
        //Then
        assertEquals(productDTO.productName(),product.getProductName());

    }
    @Test
    public void shouldMapProductDtoToProduct()
    {      //Given
        ProductDTO productDTO=new ProductDTO("Milk");
        //When
        Product product = productMapper.toEntity(productDTO);
        //Then
        assertEquals(productDTO.productName(),product.getProductName());

    }
    @Test
    public void shouldMapListProductDtoToListProduct()
    {
        List<Product> productS=new ArrayList<>();
        productMapper.toDTOs(productS);
    }

}