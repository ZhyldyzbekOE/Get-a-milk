package com.company;

import com.company.enums.ProductStatus;
import java.util.Scanner;
import com.company.services.OrderOperationServices;

public class Main {

    public static void main(String[] args) {

        String ans;
        Scanner scan = new Scanner(System.in);
        OrderOperationServices orderOperationServices = OrderOperationServices.INSTANCE;

        while (true){
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Выберите дейтсвие: ");
            System.out.println("1. Заказать продукты\n2. Закрыть операцию заказа\n3. Посмотерть список своих заказов\n4. Режим работы\n5. Удаление заказа.\n6. Выход.");
            System.out.print("Ваш выбор: ");
            byte choose = scan.nextByte();
            if (choose == 1){
                int unNum = (int) ((Math.random() * (10000 - 1)) + 1);
                System.out.println("Уникальный номер вашего заказа. Сохраните этот номер: " + unNum);
                do{
                    System.out.print("Хотите оформить заказ? yes/no - ");
                    ans = scan.next();
                    if (ans.equals("no")){
                        break;
                    }
                    System.out.print("Название продукта: ");
                    scan.nextLine();
                    String productName = scan.nextLine();
                    System.out.print("Количество: ");
                    int amount = scan.nextInt();
                    System.out.print("GR, KG, LTR, PIECE. Выберите измерения: ");
                    String unit = scan.next();
                    ProductStatus productStatus = ProductStatus.valueOf(unit);
                    String id = String.valueOf(unNum);
                    orderOperationServices.createProductOrderServices(productName, amount, productStatus, id);
                }while(ans.equals("yes"));
            }else if(choose == 2){
                System.out.print("Для закрытия операции введите уникальный номер: ");
                String idUser = scan.next();
                try {
                    orderOperationServices.closedOpenOrderOperation(idUser);
                }catch (RuntimeException ex){
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("Операция по данному номеру уже закрыта, или не существует!");
                }
            }else if (choose == 3){
                try {
                    System.out.print("Для просмотра введите уникальный номер заказа: ");
                    String idCar = scan.next();
                    orderOperationServices.showListByIdCard(idCar);
                }catch (RuntimeException ex){
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("На данный момент у вас не заказов!");
                }
            }else if (choose == 4){
                orderOperationServices.findOpenOperationForEmployee();
            }else if (choose == 5){
                try {
                    System.out.print("Для удаления введите уникальный номер заказа: ");
                    String idRemove = scan.next();
                    orderOperationServices.removeOperationByIdCard(idRemove);
                }catch (RuntimeException e){
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("По данному ID, операция не найдена! ");
                    System.out.println("---------------------------------------------------------------------------------------");
                }

            }
            else if (choose==6){
                System.out.println("До свидания!");
                break;
            }
            else{
                System.out.println("Некорректная команда!");
            }
        }
    }
}

