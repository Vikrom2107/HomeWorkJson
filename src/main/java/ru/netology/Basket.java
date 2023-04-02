package ru.netology;

import java.io.*;

public class Basket {
    private String[] products;
    private int[] prices;
    private int[] count;
    private int sumProducts = 0;

    public Basket(String[] products, int [] prices) {
        this.products = products;
        this.prices = prices;
        this.count = new int[products.length];
    }
    public void addToCart(int productNumber, int productCount) {
        count[productNumber - 1] += productCount;
        sumProducts += productCount * prices[productNumber - 1];

    }
    public void getList() {
        System.out.println("Список продуктов:");
        for (int i = 0; i< products.length; i++) {
            System.out.println((i+1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
    }
    public void printCart() {
        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + count[i] + " шт "
                    + prices[i] + " руб/шт Cумма " + (count[i] * prices[i]) + " руб");
        }
        System.out.println("Общая сумма продуктов " + sumProducts + " руб");
    }

    public void setProducts(String[] products) {
        this.products = products;
    }
    public String[] getProducts() {
        return products;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public void setSumProducts(int sumProducts) {
        this.sumProducts = sumProducts;
    }



    public void saveTxt(File textFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(textFile))){
            for (String prod : products) {
                bw.write(prod + ";");
            }
            bw.newLine();
            for (int c : count) {
                bw.write(c + ";");
            }
            bw.newLine();
            bw.write(sumProducts + "");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static Basket loadFromTxtFile(File textFile) {
        if (textFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
                Basket basket = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Гречневая крупа"},
                        new int[]{50, 150, 100, 200});
                basket.setProducts(br.readLine().split(";"));
                try {
                    String[] numbers = br.readLine().split(";");
                    int [] count = new int[numbers.length];
                    for (int i = 0; i < numbers.length; i++) {
                        try {
                            count[i] = Integer.parseInt(numbers[i]);
                        } catch (Exception e) {
                            System.out.println("Не распарсилась строка 2");
                        }
                    }
                    basket.setCount(count);
                    basket.setSumProducts(Integer.parseInt(br.readLine()));
                } catch (Exception e) {
                    System.out.println("Не распарсились строки");
                }
                return basket;

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                textFile.createNewFile();
                Basket basket2 = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Гречневая крупа"},
                        new int[]{50, 150, 100, 200});
                return basket2;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Гречневая крупа"},
                new int[]{50, 150, 100, 200});
    }
}

