package org.skypro.skyshop.product;

import java.util.function.BinaryOperator;

public abstract class Product {
    private final String name;
    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    @Override
    public abstract String toString();

    public abstract boolean isSpecial();

}
