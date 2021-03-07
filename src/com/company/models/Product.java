package com.company.models;

import com.company.enums.ProductStatus;

public class Product {

    private String name;
    private double id;
    private ProductStatus productAmount;
    private double amount;

    public Product(String name, double amount, ProductStatus productAmount) {
        this.name = name;
        this.amount = amount;
        this.productAmount = productAmount;
        this.id = Math.random();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getId() {
        return id;
    }

    public ProductStatus getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(ProductStatus productAmount) {
        this.productAmount = productAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
