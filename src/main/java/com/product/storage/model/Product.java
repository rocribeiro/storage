package com.product.storage.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantityInStock;
    private double unitPrice;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    @ManyToOne
    private Supplier supplier;
    
    public Product(){

    }

    
    public Product(Long id, String name, int quantityInStock, double unitPrice, LocalDateTime creationDate,
            LocalDateTime lastUpdateDate, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.supplier = supplier;
    }


    public Product(Long id, String name, int quantityInStock, double unitPrice, LocalDateTime creationDate,
            LocalDateTime lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


}
