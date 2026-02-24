package org.skypro.skyshop.product;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не должно быть пустым или состоять из пробелов!");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    @Override
    public abstract String toString();

    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getTypeSearchable() {
        return "PRODUCT";
    }

    @Override
    public String getNameSearchable() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
