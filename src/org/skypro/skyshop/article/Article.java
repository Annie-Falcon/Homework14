package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;

public final class Article implements Searchable {
    private final String nameArticle;
    private final String textArticle;

    public Article(String nameArticle, String textArticle) {
        this.nameArticle = nameArticle;
        this.textArticle = textArticle;
    }

    @Override
    public String toString() {
        return "'" + nameArticle + "' " + textArticle;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getTypeSearchable() {
        return "ARTICLE";
    }

    @Override
    public String getNameSearchable() {
        return "'" + nameArticle + "'";
    }
}
