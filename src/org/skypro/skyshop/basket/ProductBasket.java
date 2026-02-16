package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductBasket {
    public LinkedList<Product> basket;

    public ProductBasket() {
        this.basket = new LinkedList<>();
    }

    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        if (product != null) {
            basket.add(product);
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

    // Метод получения количества специальных товаров
    public int getSpecialProduct() {
        int total = 0;
        for (Product product : basket) {
            if (product == null || !product.isSpecial()) {
                continue;
            }
            total++;
        }
        return total;
    }

    // Проверка пустой корзины
    public boolean isEmptyBasket() {
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
                    System.out.println(product);
                }
            }
            System.out.println("Итого:" + getTotalPrice() + " руб.");
            System.out.println("Специальных товаров:" + getSpecialProduct() + " руб.");
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
        basket.clear();
        System.out.println("Корзина очищена");
    }

    // Метод удаления продукта по имени из корзины
    public List<Product> removeByName(String name) {
        List<Product> listRemove = new LinkedList<>();
        if (name == null || name.isBlank()) {
            System.out.println("Название удаляемого продукта не должно быть пустым или состоять из пробелов!");
        } else {
            Iterator<Product> copyBasket = basket.iterator();
            while (copyBasket.hasNext()) {
                Product element = copyBasket.next();
                if (element.getName().equals(name)) {
                    listRemove.add(element);
                    copyBasket.remove();
                }
            }
        }
        return listRemove;
    }

}
