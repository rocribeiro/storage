package com.product.storage.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.storage.exception.ProductNotFoundException;
import com.product.storage.model.Product;
import com.product.storage.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        
        try {
            Product existingProduct = productService.findProductById(id);
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setQuantityInStock(updatedProduct.getQuantityInStock());
            existingProduct.setUnitPrice(updatedProduct.getUnitPrice());
            existingProduct.setLastUpdateDate(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(existingProduct));
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
         try {
             productService.findProductById(id);
             productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
         } catch (ProductNotFoundException ex) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
         }
       
    }

    @PostMapping("/{id}/increase")
    public ResponseEntity<Object> increaseStock(@PathVariable Long id, @RequestParam int quantity) {
        try {
            Product existingProduct = productService.findProductById(id);
             existingProduct.setQuantityInStock(existingProduct.getQuantityInStock()+quantity);
            existingProduct.setLastUpdateDate(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(existingProduct));
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }       
    }

    @PostMapping("/{id}/decrease")
    public ResponseEntity<Object> decreaseStock(@PathVariable Long id, @RequestParam int quantity) {

        try {
            Product existingProduct = productService.findProductById(id);
             var quantityInStock = existingProduct.getQuantityInStock() - quantity;
            if( quantityInStock < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock cannot be negative.");
            }
            existingProduct.setQuantityInStock(quantityInStock);
            existingProduct.setLastUpdateDate(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(existingProduct));
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }

    
    
    
}
