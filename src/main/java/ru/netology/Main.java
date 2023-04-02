package ru.netology;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("basket.txt");
        Basket basket = Basket.loadFromTxtFile(file);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int productNumber = 0;
            int productCount = 0;
            System.out.println("Введите команду (1. Добавить товар 2. Показать корзину 3. end)");
            String input = scanner.nextLine();
            if ("3".equals(input) || "end".equals(input)) {
                basket.printCart();
                break;
            } else if ("1".equals(input) || "Добавить товар".equals(input)) {
                while (true) {
                    System.out.println("Выберите товар, указав необходимый порядковый номер, " +
                            "и введите количество продуктов");
                    basket.getList();
                    String addProduct = scanner.nextLine();
                    String[] numbers = addProduct.split(" ");
                    if (numbers.length != 2) {
                        System.out.println("Вы не ввели порядковый номер или количество продуктов, пожалуйста" +
                                " введите 2 значения\n");
                        continue;
                    }
                    try {
                        productNumber = Integer.parseInt(numbers[0]);
                        productCount = Integer.parseInt(numbers[1]);
                        if (productNumber > basket.getProducts().length || productNumber < 1) {
                            System.out.println("В списке нет продуктов под таким номером, пожалуйста введите " +
                                    "порядковый номер из представленных в списке\n");
                            continue;
                        }
                        if (productCount <= 0) {
                            System.out.println("Количество продуктов должно быть больше 0\n");
                            continue;
                        }
                        basket.addToCart(productNumber, productCount);
                        basket.saveTxt(file);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели некорректные значения, пожалуйста введите " +
                                "2 целых числа через пробел\n");
                    }
                }
            } else if ("2".equals(input) || "Показать корзину".equals(input)) {
                basket.printCart();
            } else {
                System.out.println("Неверно введена команда");
            }
        }
    }

}