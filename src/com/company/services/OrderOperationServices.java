package com.company.services;

import com.company.enums.ProductStatus;
import com.company.models.OrderOperation;
import com.company.models.Product;
import com.company.services.impl.OrderOperationServicesImpl;

public interface OrderOperationServices {

    OrderOperationServices INSTANCE = new OrderOperationServicesImpl();
    void createOrderServices(String name, double amount, ProductStatus productStatus, String idCard);
    OrderOperation closedOrderOperation(String idCard);
    OrderOperation showListByIdCard(String idCard);
}
