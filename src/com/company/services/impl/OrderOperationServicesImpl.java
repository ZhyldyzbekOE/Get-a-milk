package com.company.services.impl;

import com.company.enums.OrderStatus;
import com.company.enums.ProductStatus;
import com.company.models.OrderOperation;
import com.company.models.Product;
import com.company.services.OrderOperationServices;

import java.util.ArrayList;

public class OrderOperationServicesImpl implements OrderOperationServices {

    ArrayList<OrderOperation> orderOperations = new ArrayList<>();

    OrderOperation findOrderOperation(String idCard){
        for (OrderOperation item:orderOperations) {
            if(item.getIdCard().equals(idCard)){
                return item;
            }
        }
        OrderOperation orderOperation = new OrderOperation(idCard);
        orderOperations.add(orderOperation);
        return orderOperation;
    }

    @Override
    public void createOrderServices(String name, double amount, ProductStatus productStatus, String idCard) {
        OrderOperation orderOperation = findOrderOperation(idCard);
        Product product = new Product(name, amount, productStatus);
        orderOperation.addProducts(product);
    }

    OrderOperation findOperationByIdCard(String idCard){
        for (OrderOperation item:orderOperations) {
            if(item.getIdCard().equals(idCard)){
                return item;
            }
        }
        throw new RuntimeException("Операция не найдена! ");
    }

    @Override
    public OrderOperation closedOrderOperation(String idCard) {
        OrderOperation orderOperation = findOperationByIdCard(idCard);
        orderOperation.setOrderStatus(OrderStatus.CLOSED);
        return orderOperation;
    }

    @Override
    public OrderOperation showListByIdCard(String idCard) {
        for (OrderOperation item:orderOperations) {
            if(item.getIdCard().equals(idCard)){
                for (Product item2:item.getProducts()) {
                    System.out.println("Название продукта: "+item2.getName()+" - "+ item2.getAmount()+" - "+ item2.getProductAmount()+". Status: "+item.getOrderStatus()+". Ваш номер: "+item.getIdCard());
                }

                return item;
            }
        }
        throw new RuntimeException("Операция по вашему номеру не найдена! ");
    }



}
