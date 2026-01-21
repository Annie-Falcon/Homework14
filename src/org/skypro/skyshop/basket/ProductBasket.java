package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Arrays;

public class ProductBasket {
    private final Product[] basket;

    public ProductBasket() {
        this.basket = new Product[5];
    }

    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        boolean basketFull = true;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] == null) {
                basket[i] = product;
                basketFull = false;
                break;
            }
        }
        if (basketFull) {
            System.out.println("Невозможно добавить продукт " + product.getName() + ". Корзина переполнена!");
        } else {
            System.out.println(product.getName() + " добавлен в корзину.");
        }
    }

    // Метод получения общей стоимости корзины
    public int getTotalPrice() {
        int total = 0;
        for (Product product : basket) {
            if (product == null) {
                continue;
            }
            total += product.getPrice();
        }
        return total;
    }

    // Проверка пустой корзины
    private boolean isEmptyBasket() {
        for (Product product : basket) {
            if (product != null) {
                return false;
            }
        }
        return true;
    }

    // Метод, который печатает содержимое корзины
    public void printBasket() {
        if (isEmptyBasket()) {
            System.out.println("В корзине пусто");
        } else {
            System.out.println("Корзина:");
            for (Product product : basket) {
                if (product != null) {
                    System.out.println(product.getName() + ": " + product.getPrice() + " руб.");
                }
            }
            System.out.println("Итого:" + getTotalPrice() + " руб.");
        }
    }

    // Метод, проверяющий продукт в корзине по имени
    public boolean isProductInBasket(String productName) {
        if (!(isEmptyBasket())) {
            for (Product product : basket) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Метод очистки корзины
    public void clearBasket() {
        Arrays.fill(basket, null);
        System.out.println("Корзина очищена");
    }

}
