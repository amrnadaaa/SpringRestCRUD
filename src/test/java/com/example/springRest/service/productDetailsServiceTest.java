package com.example.springRest.service;

import com.example.springRest.dto.ProductDetailsDTO;
import com.example.springRest.entity.Product;
import com.example.springRest.entity.ProductDetails;
import com.example.springRest.exception.ProductNotFoundException;
import com.example.springRest.mapper.productDetailsMapper;
import com.example.springRest.repository.productDetailsRepository;
import com.example.springRest.repository.productRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class productDetailsServiceTest {
    // the dependencies productDetailsService needed
    @Mock
    private productDetailsRepository productDetailsRepository;
    @Mock
    private productDetailsMapper productDetailsMapper;
    @Mock
    private productRepository productRepository;
    //The service i will test
    @InjectMocks
    private productDetailsService productDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductDetails() {
        // Given
        ProductDetails details = new ProductDetails();
        ProductDetails saved = new ProductDetails();
        ProductDetailsDTO dto = new ProductDetailsDTO("Milk", 10.0, 1.0, "Egypt");
        // Mock the Calls
        when(productDetailsRepository.save(details)).thenReturn(saved);
        when(productDetailsMapper.toDTO(saved)).thenReturn(dto);

        // when
        ProductDetailsDTO result = productDetailsService.addProductDetails(details);

        // Then
        assertSame(dto, result);
        verify(productDetailsRepository).save(details);
        verify(productDetailsMapper).toDTO(saved);
    }

    @Test
    void testUpdateProductDetails_Success() throws ProductNotFoundException {
        //Given
        Long productId = 1L;

        Product existingProduct = new Product();
        existingProduct.setId(productId);

        ProductDetails existingDetails = new ProductDetails();
        ProductDetails updateRequest = new ProductDetails();

        ProductDetails savedDetails = new ProductDetails();
        savedDetails.setDescription("Updated Desc");

        ProductDetailsDTO dto =
                new ProductDetailsDTO("Updated Desc", 10.0, 1.0, "Egypt");
         // mock the calls
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productDetailsRepository.findByProduct(existingProduct)).thenReturn(existingDetails);

        doNothing().when(productDetailsMapper).updateFromRequest(updateRequest, existingDetails);

        when(productDetailsRepository.save(existingDetails)).thenReturn(savedDetails);
        when(productDetailsMapper.toDTO(savedDetails)).thenReturn(dto);

        // When
        ProductDetailsDTO result =
                productDetailsService.updateProductDetails(updateRequest, productId);

        // Then
        assertSame(dto, result);

        verify(productRepository).findById(productId);
        verify(productDetailsRepository).findByProduct(existingProduct);
        verify(productDetailsMapper).updateFromRequest(updateRequest, existingDetails);
        verify(productDetailsRepository).save(existingDetails);  // IMPORTANT
    }

    @Test
    void testUpdateProductDetails_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productDetailsService.updateProductDetails(new ProductDetails(), 1L));
    }

    @Test
    void testDeleteProductDetails_Success() throws ProductNotFoundException {
        //Given
        Long id = 1L;

        Product product = new Product();
        ProductDetails details = new ProductDetails();
        product.setProductDetails(details);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // When
        productDetailsService.deleteProductDetails(id);

        // Then
        assertNull(product.getProductDetails()); // productDetails removed
        verify(productRepository).save(product);
        verify(productDetailsRepository).delete(details);
    }

    @Test
    void testDeleteProductDetails_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productDetailsService.deleteProductDetails(1L));
    }

    @Test
    void testDeleteProductDetails_DetailsNotFound() {
        Product product = new Product();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(ProductNotFoundException.class,
                () -> productDetailsService.deleteProductDetails(1L));
    }

    @Test
    void testUpdateProductDetailsPartial_Success() throws ProductNotFoundException {
        //Given
        Long productId = 1L;

        Product product = new Product();
        ProductDetails existingDetails = new ProductDetails();
        product.setProductDetails(existingDetails);

        ProductDetails request = new ProductDetails();
        ProductDetails saved = new ProductDetails();

        ProductDetailsDTO dto =
                new ProductDetailsDTO("Updated", 20.0, 1.5, "USA");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productDetailsMapper).updateFromRequest(request, existingDetails);
        when(productDetailsRepository.save(existingDetails)).thenReturn(saved);
        when(productDetailsMapper.toDTO(saved)).thenReturn(dto);

        // Whn
        ProductDetailsDTO result =
                productDetailsService.updateProductDetailsPartial(request, productId);

        // Then
        assertSame(dto, result);
        verify(productDetailsMapper).updateFromRequest(request, existingDetails);
        verify(productDetailsRepository).save(existingDetails);
    }

}