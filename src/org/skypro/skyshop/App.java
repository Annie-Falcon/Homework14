package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        Product bread = new Product("Хлеб", 120);
        Product apple = new Product("Яблоко", 20);
        Product milk = new Product("Молоко", 80);
        Product coffee = new Product("Кофе", 300);
        Product banana = new Product("Банан", 30);
        Product salt = new Product("Соль", 50);
        ProductBasket basket1 = new ProductBasket();

        System.out.println("Заполнение корзины");
        basket1.addProduct(bread);
        basket1.addProduct(salt);
        basket1.addProduct(milk);
        basket1.addProduct(apple);
        basket1.addProduct(banana);
        basket1.addProduct(coffee);

        System.out.println(" ");
        basket1.printBasket();
        System.out.println("Наличие кофе в корзине: " + basket1.isProductInBasket("кофе"));
        System.out.println("Наличие соли в корзине: " + basket1.isProductInBasket("соль"));

        System.out.println(" ");
        basket1.clearBasket();
        basket1.printBasket();
        System.out.println("Стоимость продуктов в корзине: " + basket1.getTotalPrice() + " руб.");
        System.out.println("Наличие хлеба в корзине: " + basket1.isProductInBasket("хлеб"));
    }
}