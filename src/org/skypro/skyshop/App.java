package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

public class App {
    public static void main(String[] args) {
        FixPriceProduct bread = new FixPriceProduct("Хлеб");
        SimpleProduct apple = new SimpleProduct("Яблоко", 20);
        DiscountedProduct milk = new DiscountedProduct("Молоко", 80, 17);
        DiscountedProduct coffee = new DiscountedProduct("Кофе", 300, 42);
        SimpleProduct banana = new SimpleProduct("Банан", 30);
        SimpleProduct salt = new SimpleProduct("Соль", 50);
        ProductBasket basket1 = new ProductBasket();

        System.out.println("Заполнение корзины");
        basket1.addProduct(bread);
        basket1.addProduct(coffee);
        basket1.addProduct(milk);
        basket1.addProduct(apple);
        basket1.addProduct(banana);
        basket1.addProduct(salt);

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