package com.company.services.impl;

import com.company.enums.OrderStatus;
import com.company.enums.ProductStatus;
import com.company.models.OrderOperation;
import com.company.models.Product;
import com.company.services.OrderOperationServices;

import java.util.ArrayList;

public class OrderOperationServicesImpl implements OrderOperationServices {

    ArrayList<OrderOperation> orderOperations = new ArrayList<>();

    OrderOperation findOrCreateOrderOperation(String idCard){
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
    public void createProductOrderServices(String name, double amount, ProductStatus productStatus, String idCard) {
        OrderOperation orderOperation = findOrCreateOrderOperation(idCard);
        Product product = new Product(name, amount, productStatus);
        orderOperation.addProducts(product);
    }

    OrderOperation findOperationByIdCard(String idCard){
        for (OrderOperation item:orderOperations) {
            if(item.getIdCard().equals(idCard) && item.getOrderStatus().equals(OrderStatus.OPEN)){
                return item;
            }
        }
        throw new RuntimeException("Операция не найдена! ");
    }

    @Override
    public OrderOperation closedOrderOperation(String idCard) {
        OrderOperation orderOperation = findOperationByIdCard(idCard);
        orderOperation.setOrderStatus(OrderStatus.CLOSED);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Операция закрыта! Спасибо что воспользовались нашими услугами!");
        System.out.println("---------------------------------------------------------------------------------------");
        return orderOperation;
    }

    @Override
    public OrderOperation showListByIdCard(String idCard) {
        OrderOperation orderOperation = findOrCreateOrderOperation(idCard);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Уникальный номер заказа: "+orderOperation.getIdCard());
        System.out.println("Статус операции: "+orderOperation.getOrderStatus());
        System.out.println("Список продуктов: ");
        for (OrderOperation item:orderOperations) {
            if(item.getIdCard().equals(idCard)){
                for (Product item2:item.getProducts()) {
                    System.out.println(item2.getName()+" - "+ item2.getAmount()+" - "+ item2.getProductAmount());
                }
                System.out.println("---------------------------------------------------------------------------------------");
                return item;
            }
        }
        throw new RuntimeException("Операция по вашему номеру не найдена! ");
    }




}
