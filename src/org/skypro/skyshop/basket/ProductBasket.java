package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    public HashMap<String, List<Product>> basket;

    public ProductBasket() {
        this.basket = new HashMap<>();
    }

    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        if (product != null) {
            basket.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
        }
    }

    // Метод получения общей стоимости корзины
    public int getTotalPrice() {
        return basket.values()
                .stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    // Метод получения количества специальных товаров
    public int getSpecialProduct() {
        return (int) basket.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    // Проверка пустой корзины
    public boolean isEmptyBasket() {
        return (basket == null || basket.isEmpty());
    }

    // Метод, который печатает содержимое корзины
    public void printBasket() {
        if (isEmptyBasket()) {
            System.out.println("В корзине пусто");
        } else {
            System.out.println("Корзина:");

            basket.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .forEach(System.out::println);

            System.out.println("Итого:" + getTotalPrice() + " руб.");
            System.out.println("Специальных товаров:" + getSpecialProduct() + " руб.");
        }
    }

    // Метод, проверяющий продукт в корзине по имени
    public boolean isProductInBasket(String productName) {
        if (!(isEmptyBasket() || productName.isBlank())) {
            return basket.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .map(Product::getName)
                    .anyMatch(i -> i.equalsIgnoreCase(productName));
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
        } else if (basket.containsKey(name)) {
            listRemove = basket.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .filter(i -> i.getName().equalsIgnoreCase(name))
                    .toList();

            basket.remove(name);
        }
        return listRemove;
    }
}
