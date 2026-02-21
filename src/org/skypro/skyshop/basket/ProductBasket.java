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
        int total = 0;
        for (Map.Entry<String, List<Product>> basketKey : basket.entrySet()) {
            for (Product product : basketKey.getValue()) {
                total += product.getPrice();
            }
        }
        return total;
    }

    // Метод получения количества специальных товаров
    public int getSpecialProduct() {
        int total = 0;
        for (Map.Entry<String, List<Product>> basketKey : basket.entrySet()) {
            for (Product product : basketKey.getValue()) {
                if (product.isSpecial()) {
                    total++;
                }
            }
        }
        return total;
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
            for (Map.Entry<String, List<Product>> basket : basket.entrySet()) {
                for (Product product : basket.getValue()) {
                    System.out.println(product);
                }
            }
            System.out.println("Итого:" + getTotalPrice() + " руб.");
            System.out.println("Специальных товаров:" + getSpecialProduct() + " руб.");
        }
    }

    // Метод, проверяющий продукт в корзине по имени
    public boolean isProductInBasket(String productName) {
        if (!(isEmptyBasket() || productName.isBlank())) {
            for (Map.Entry<String, List<Product>> basket : basket.entrySet()) {
                for (Product product : basket.getValue()) {
                    if (product.getName().equalsIgnoreCase(productName)) {
                        return true;
                    }
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
           /* basket.entrySet().removeIf(entry -> {
                if (entry.getKey().equalsIgnoreCase(name)) {
                    listRemove.addAll(entry.getValue());
                    return true;
                } else {
                    return false;
                }
            });*/

            if (basket.containsKey(name)) {
                for (Map.Entry<String, List<Product>> basket : basket.entrySet()) {
                    for (Product product : basket.getValue()) {
                        if (product.getName().equalsIgnoreCase(name)) {
                            listRemove.add(product);
                        }
                    }
                }
                basket.remove(name);
            }
        }
        return listRemove;
    }
}
