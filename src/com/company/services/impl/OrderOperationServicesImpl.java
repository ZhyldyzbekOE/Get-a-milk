package com.company.services.impl;

import com.company.enums.OrderStatus;
import com.company.enums.ProductStatus;
import com.company.models.OrderOperation;
import com.company.models.Product;
import com.company.services.OrderOperationServices;

import java.util.ArrayList;
import java.util.Scanner;


public class OrderOperationServicesImpl implements OrderOperationServices {

    Scanner scan= new Scanner(System.in);
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

    // для закрытия
    OrderOperation findOpenOperationByIdCard(String idCard){
        for (OrderOperation item:orderOperations) {
            if(item.getIdCard().equals(idCard) && item.getOrderStatus().equals(OrderStatus.OPEN)){
                return item;
            }
        }
        throw new RuntimeException("Операция не найдена! ");
    }

    @Override
    public OrderOperation closedOpenOrderOperation(String idCard) {
        OrderOperation orderOperation = findOpenOperationByIdCard(idCard);
        orderOperation.setOrderStatus(OrderStatus.CLOSED);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Заказ закрыт! Спасибо что воспользовались нашими услугами!");
        System.out.println("---------------------------------------------------------------------------------------");
        return orderOperation;
    }

    @Override
    public OrderOperation showListByIdCard(String idCard) {
        OrderOperation orderOperation = findOperationByIdCard(idCard);
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
        throw new RuntimeException("Заказ по вашему номеру не найдена! ");
    }

    // для удаления
    private OrderOperation findOperationByIdCard(String idCard) {
        for (OrderOperation item:orderOperations) {
            if(item.getIdCard().equals(idCard)){
                return item;
            }
        }
        throw new RuntimeException("Операция не найдена! ");
    }

    @Override
    public void removeOperationByIdCard(String idCard) {
        OrderOperation orderOperation = findOperationByIdCard(idCard);
        orderOperations.remove(orderOperation);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Заказ успешно удален! ");
    }

    public void findOpenOperationForEmployee() {
        String ans;
        do {
            System.out.print("Продолжить работу в этом режиме? yes/no - ");
            ans = scan.next();
            if (ans.equals("no")){
                break;
            }
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Открытые заказы: ");
        for (OrderOperation item:orderOperations) {
            if (item.getOrderStatus().equals(OrderStatus.OPEN)){
                System.out.println("ID заказа: "+item.getIdCard() +". Статус: " + item.getOrderStatus());
            }
        }
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Выберите дейтсвие: ");
            System.out.println("1. Просмотр продуктов: \n2. Выполнить заказ\n3. Выход из режима");
            System.out.print("Ваш выбор: ");
            byte choose = scan.nextByte();
            if (choose==1){
                try {
                    System.out.print("Для просмотра продуктов выберите ID: ");
                    String idCard = scan.next();
                    OrderOperation orderOperation = showListByIdCard(idCard);
                }catch (RuntimeException e){
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("По данному ID, операция не найдена! ");
                    System.out.println("---------------------------------------------------------------------------------------");
                }
            }else if(choose == 2){
                try {
                    System.out.print("Введите ID выбранного заказа: ");
                    String id = scan.next();
                    OrderOperation orderOperation = findOpenOperationByIdCard(id);
                    orderOperation.setOrderStatus(OrderStatus.PROCESS);
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("Можете приступать к выполнению заказа! ");
                    System.out.println("---------------------------------------------------------------------------------------");
                }catch (RuntimeException e){
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("По данному ID, операция не найдена! ");
                    System.out.println("---------------------------------------------------------------------------------------");
                }

            }else if (choose == 3){
                break;
            }else {
                System.out.println("Некорректная команда!");
            }
        }while (ans.equals("yes"));
    }
}
