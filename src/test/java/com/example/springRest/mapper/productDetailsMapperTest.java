package com.example.springRest.mapper;

import com.example.springRest.dto.ProductDetailsDTO;
import com.example.springRest.entity.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class productDetailsMapperTest {
    private productDetailsMapper productDetailsMapper;

    @BeforeEach
    void setUp() {
        productDetailsMapper=new productDetailsMapperImpl();
    }
    @Test
    void testToEntity() {
        // Given
        ProductDetailsDTO dto = new ProductDetailsDTO(
                "Fresh Milk",
                25.50,
                1.2,
                "Egypt"
        );

        // When
        ProductDetails entity = productDetailsMapper.toEntity(dto);

        // Then
        assertNull(entity.getId());
        assertEquals("Fresh Milk", entity.getDescription());
        assertEquals(25.50, entity.getPrice());
        assertEquals(1.2, entity.getWeight_kg());
        assertEquals("Egypt", entity.getCountry_of_origin());
        assertEquals(0, entity.getStock_quantity());
        assertNull(entity.getCreated_at());
        assertNull(entity.getUpdated_at());
        assertNull(entity.getProduct());
    }
    @Test
    void testToDTO() {
        // Given Entity
        ProductDetails entity = new ProductDetails();
        entity.setId(10L);
        entity.setDescription("Raw Honey");
        entity.setPrice(150.0);
        entity.setWeight_kg(0.75);
        entity.setCountry_of_origin("Yemen");
        entity.setStock_quantity(50);
        entity.setCreated_at(new Date());
        entity.setUpdated_at(new Date());

        // When
        ProductDetailsDTO dto = productDetailsMapper.toDTO(entity);

        // Then
        assertEquals("Raw Honey", dto.description());
        assertEquals(150.0, dto.price());
        assertEquals(0.75, dto.weight_kg());
        assertEquals("Yemen", dto.country_of_origin());
    }

    @Test
    void testUpdateFromRequest() {
        // Given → Source (partial update)
        ProductDetails source = new ProductDetails();
        source.setDescription("Updated Description");
        source.setPrice(99.99);
        source.setWeight_kg(2.0);
        // Given →
        ProductDetails target = new ProductDetails();
        target.setId(5L);
        target.setDescription("Old Desc");
        target.setPrice(20.0);
        target.setWeight_kg(1.0);
        target.setCountry_of_origin("Brazil");
        target.setStock_quantity(100);
        target.setCreated_at(new Date());
        target.setUpdated_at(new Date());
        // When
        productDetailsMapper.updateFromRequest(source, target);
        // Then
        assertEquals(5L, target.getId()); // ID must stay unchanged
        assertEquals("Updated Description", target.getDescription());
        assertEquals(99.99, target.getPrice());
        assertEquals(2.0, target.getWeight_kg());
        assertEquals("Brazil", target.getCountry_of_origin());
        assertEquals(100, target.getStock_quantity());
        assertNotNull(target.getCreated_at());
        assertNotNull(target.getUpdated_at());
    }
}