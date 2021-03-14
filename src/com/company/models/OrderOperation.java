package com.company.models;

import com.company.enums.OrderStatus;

import java.util.ArrayList;

public class OrderOperation {

    private String idCard;
    private OrderStatus orderStatus;
    private ArrayList<Product>products;
    private double id;

    public OrderOperation(String idCard) {
        this.idCard = idCard;
        this.orderStatus = OrderStatus.OPEN;
        this.products = new ArrayList<>();
        this.id = Math.random();
    }

    public String getIdCard() {
        return idCard;
    }

    public double getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProducts(Product product) {
        products.add(product);
    }


}
