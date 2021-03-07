package com.company;

import com.company.enums.ProductStatus;
import java.util.Scanner;
import com.company.services.OrderOperationServices;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        OrderOperationServices orderOperationServices = OrderOperationServices.INSTANCE;

        while (true){
            System.out.println("Выберите дейтсвие: ");
            System.out.println("1. Заказать продукты\n2. Закрыть операцию заказа\n3. Посмотерть список своих заказов\n4. Выход.");
            System.out.print("Ваш выбор: ");
            byte choose = scan.nextByte();
            if (choose == 1){
                System.out.print("Название продукта: ");
                scan.nextLine();
                String productName = scan.nextLine();
                System.out.print("Количество: ");
                int amount = scan.nextInt();
                System.out.print("GR, KG, LTR, PIECE. Выберите измерения: ");
                String unit = scan.next();
                ProductStatus productStatus = ProductStatus.valueOf(unit);
                System.out.print("Введите ваш номер: ");
                String id = scan.next();
                orderOperationServices.createOrderServices(productName, amount, productStatus, id);
            }else if(choose == 2){
                System.out.print("Для закрытия операции введите ваш номер: ");
                String idUser = scan.next();
                orderOperationServices.closedOrderOperation(idUser);
            }else if (choose == 3){
                System.out.print("Введи ваш номер: ");
                String idCar = scan.next();
                orderOperationServices.showListByIdCard(idCar);
            }else if (choose == 4){
                System.out.println("До свидания!");
                break;
            }else{
                System.out.println("Некорректная команда!");
            }
        }
    }
}