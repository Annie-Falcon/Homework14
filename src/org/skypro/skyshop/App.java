package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exeptions.BestResultNotFound;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class App {
    public static FixPriceProduct bread = new FixPriceProduct("хлеб");
    public static SimpleProduct apple = new SimpleProduct("яблоко", 20);
    public static DiscountedProduct milk = new DiscountedProduct("молоко", 80, 17);
    public static DiscountedProduct coffee = new DiscountedProduct("кофе", 300, 42);
    public static SimpleProduct banana = new SimpleProduct("банан", 30);
    public static SimpleProduct salt = new SimpleProduct("соль", 50);
    public static SimpleProduct breadS = new SimpleProduct("хлеб", 75);
    public static DiscountedProduct breadD = new DiscountedProduct("хлеб", 64, 35);

    public static Article arBread = new Article("Полезный хлеб", "хлеб подходит к напиткам: кофе, молоко");
    public static Article arMilk = new Article("Завтрак с молоком", "Смузи - в молоко добавьте: банан, яблоко; подавайте к кофе с молоком");
    public static Article arCoffee = new Article("Рецепт кофе с молоком", "Добавьте: в кофе молоко");
    public static Article arBanana = new Article("Ищите банан", "Увидите яблоко - где-то рядом банан. Не забудьте молоко");
    public static Article arApple = new Article("Богатырское яблоко", "Полезнее, чем банан. Для смузи используйте кефир, не молоко");

    public static void main(String[] args) {
        realizationProductBasket();
        System.out.println(" ");
        realizationSearchEngine();
        System.out.println(" ");
        realizationException();
    }

    private static void realizationProductBasket() {
        ProductBasket basket1 = new ProductBasket();

        // Заполнение корзины
        basket1.addProduct(bread);
        basket1.addProduct(coffee);
        basket1.addProduct(milk);
        basket1.addProduct(apple);
        basket1.addProduct(breadD);
        basket1.addProduct(banana);
        basket1.addProduct(salt);
        basket1.addProduct(breadS);

        basket1.printBasket();
        System.out.println("Наличие кофе в корзине: " + basket1.isProductInBasket("кофе"));
        System.out.println("Наличие вишни в корзине: " + basket1.isProductInBasket("вишня"));

        deleteProductFromBasket(basket1, "хлеб");
        deleteProductFromBasket(basket1, "вишня");

        System.out.println(" ");
        basket1.clearBasket();
        basket1.printBasket();
        System.out.println("Стоимость продуктов в корзине: " + basket1.getTotalPrice() + " руб.");
        System.out.println("Наличие хлеба в корзине: " + basket1.isProductInBasket("хлеб"));
    }

    public static void deleteProductFromBasket(ProductBasket basket, String name) {
        if (basket == null || basket.isEmptyBasket()) {
            System.out.println("Корзина пуста!");
        } else {
            System.out.println(" ");
            System.out.println("Удаляем из корзины '" + name + "'");
            printListProduct(basket.removeByName(name));
            System.out.println(" ");
            basket.printBasket();
        }
    }

    public static void printListProduct(List<Product> listProduct) {
        if (listProduct == null || listProduct.isEmpty()) {
            System.out.println("Список пуст!");
        } else {
            for (Product product : listProduct) {
                System.out.println(product);
            }
        }
    }

    private static void realizationSearchEngine() {
        SearchEngine searchEngine1 = new SearchEngine();

        System.out.println("Заполнение листа поиска 1");
        searchEngine1.add(bread);
        searchEngine1.add(coffee);
        searchEngine1.add(milk);
        searchEngine1.add(banana);
        searchEngine1.add(arBread);
        searchEngine1.add(arMilk);
        searchEngine1.add(arCoffee);
        searchEngine1.add(arBanana);
        searchEngine1.add(arApple);
        searchEngine1.add(salt);

        getSearch(searchEngine1, "молоко");
        getSearch(searchEngine1, " ");
        getSearch(searchEngine1, "банан");
    }

    public static void getSearch(SearchEngine searchEngine, String searchString) {
        System.out.println(" ");
        System.out.println("Результат поиска: '" + searchString + "'");
        TreeMap<String, Searchable> listResult = searchEngine.search(searchString);
        for (Map.Entry<String, Searchable> searchResult : listResult.entrySet()) {
            System.out.println(searchResult.getKey() + ": " + searchResult.getValue());
        }
    }

    private static void realizationException() {
        System.out.println("Новые продукты");
        try {
            //SimpleProduct cherry = new SimpleProduct("вишня", -20);
            //SimpleProduct eggs = new SimpleProduct("  ", 139);
            //DiscountedProduct tea = new DiscountedProduct("", 280, 17);
            //DiscountedProduct potato = new DiscountedProduct("картофель", -80, 10);
            //DiscountedProduct muesli = new DiscountedProduct("мюсли", 230, 115);
            DiscountedProduct tomato = new DiscountedProduct(null, -100, 145);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Добавление новых продуктов завершено");
        System.out.println(" ");

        SearchEngine searchEngine2 = new SearchEngine();

        System.out.println("Заполнение листа поиска 2");
        searchEngine2.add(arBread);
        searchEngine2.add(arMilk);
        searchEngine2.add(arCoffee);
        System.out.println(" ");
        getSearchMax(searchEngine2, "молоко");
        getSearchMax(searchEngine2, "соль");
        getSearchMax(searchEngine2, "кофе");
    }

    public static void getSearchMax(SearchEngine searchEngine, String searchString) {
        try {
            Searchable resultMax = searchEngine.searchMaxCoincidence(searchString);
            System.out.println("Результат поиска максимального кол-ва вхождения слова '" + searchString + "': " + resultMax.getNameSearchable());
        } catch (BestResultNotFound e) {
            System.out.println(e.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}