package org.skypro.skyshop.exeptions;

public class BestResultNotFound extends Exception {
    final String searchString;

    public BestResultNotFound(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String toString() {
        return "BestResultNotFound{ Для слова " + searchString + " не нашлось подходящей статьи! }";
    }
}
