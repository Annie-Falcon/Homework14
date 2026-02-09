package org.skypro.skyshop.search;

public interface Searchable {
    default String getStringRepresentation() {
        return getNameSearchable() + " - " + getTypeSearchable();
    }

    String getSearchTerm();

    String getTypeSearchable();

    String getNameSearchable();
}
