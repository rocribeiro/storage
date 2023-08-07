package com.product.storage.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.storage.exception.ProductNotFoundException;
import com.product.storage.model.Product;
import com.product.storage.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

      @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Produto 1", 10, 19.99, LocalDateTime.now(), LocalDateTime.now()));
        productList.add(new Product(2L, "Produto 2", 5, 9.99, LocalDateTime.now(), LocalDateTime.now()));

        when(productService.findAllProducts()).thenReturn(productList);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());

        verify(productService, times(1)).findAllProducts();
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;

        Product product = new Product(productId, "Produto Teste", 5, 10.0, LocalDateTime.now(), LocalDateTime.now());

        when(productService.findProductById(productId)).thenReturn(product);

        ResponseEntity<Object> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());

        verify(productService, times(1)).findProductById(productId);
    }

     @Test
    public void testCreateProduct() {
        Product product = new Product(null, "Novo Produto", 10, 25.0, LocalDateTime.now(), LocalDateTime.now());

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        ResponseEntity<Object> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;
        Product updatedProduct = new Product(productId, "Produto Atualizado", 15, 30.0, LocalDateTime.now(), LocalDateTime.now());

        when(productService.findProductById(productId)).thenReturn(new Product());

        when(productService.saveProduct(any(Product.class))).thenReturn(updatedProduct);

        ResponseEntity<Object> response = productController.updateProduct(productId, updatedProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct_ProductNotFound() {
        Long productId = 1L;
        Product updatedProduct = new Product(productId, "Produto Atualizado", 15, 30.0, LocalDateTime.now(), LocalDateTime.now());

        when(productService.findProductById(productId)).thenThrow(new ProductNotFoundException("Product not found."));

        ResponseEntity<Object> response = productController.updateProduct(productId, updatedProduct);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found.", response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, never()).saveProduct(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;

        when(productService.findProductById(productId)).thenReturn(new Product());

        ResponseEntity<Object> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully.", response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    public void testDeleteProduct_ProductNotFound() {
        Long productId = 1L;

        when(productService.findProductById(productId)).thenThrow(new ProductNotFoundException("Product not found."));

        ResponseEntity<Object> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found.", response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, never()).deleteProduct(productId);
    }
    @Test
    public void testIncreaseStock() {
        Long productId = 1L;
        int quantityToAdd = 5;
        Product existingProduct = new Product(productId, "Produto Existente", 10, 20.0, LocalDateTime.now(), LocalDateTime.now());

        when(productService.findProductById(productId)).thenReturn(existingProduct);

        when(productService.saveProduct(any(Product.class))).thenReturn(existingProduct);

        ResponseEntity<Object> response = productController.increaseStock(productId, quantityToAdd);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingProduct, response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    public void testIncreaseStock_ProductNotFound() {
        Long productId = 1L;
        int quantityToAdd = 5;

        when(productService.findProductById(productId)).thenThrow(new ProductNotFoundException("Product not found."));

        ResponseEntity<Object> response = productController.increaseStock(productId, quantityToAdd);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found.", response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, never()).saveProduct(any(Product.class));
    }

    @Test
    public void testDecreaseStock() {
        Long productId = 1L;
        int quantityToDecrease = 3;
        Product existingProduct = new Product(productId, "Produto Existente", 10, 20.0, LocalDateTime.now(), LocalDateTime.now());

        when(productService.findProductById(productId)).thenReturn(existingProduct);

        when(productService.saveProduct(any(Product.class))).thenReturn(existingProduct);

        ResponseEntity<Object> response = productController.decreaseStock(productId, quantityToDecrease);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingProduct, response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    public void testDecreaseStock_ProductNotFound() {
        Long productId = 1L;
        int quantityToDecrease = 3;

        when(productService.findProductById(productId)).thenThrow(new ProductNotFoundException("Product not found."));

        ResponseEntity<Object> response = productController.decreaseStock(productId, quantityToDecrease);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found.", response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, never()).saveProduct(any(Product.class));
    }
    @Test
    public void testDecreaseStock_NegativeStock() {
        Long productId = 1L;
        int quantityToDecrease = 15;
        Product existingProduct = new Product(productId, "Produto Existente", 10, 20.0, LocalDateTime.now(), LocalDateTime.now());

        when(productService.findProductById(productId)).thenReturn(existingProduct);

        ResponseEntity<Object> response = productController.decreaseStock(productId, quantityToDecrease);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Stock cannot be negative.", response.getBody());

        verify(productService, times(1)).findProductById(productId);
        verify(productService, never()).saveProduct(any(Product.class));
    }
}
