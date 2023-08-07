package com.product.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.storage.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

