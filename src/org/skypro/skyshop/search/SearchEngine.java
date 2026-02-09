package org.skypro.skyshop.search;

import java.util.Arrays;

public class SearchEngine {
    private final Searchable[] listSearch;

    public SearchEngine(int size) {
        this.listSearch = new Searchable[size];
    }

    public void add(Searchable searchable) {
        boolean listFull = true;
        for (int i = 0; i < listSearch.length; i++) {
            if (listSearch[i] == null) {
                listSearch[i] = searchable;
                listFull = false;
                break;
            }
        }
        if (listFull) {
            System.out.println("Невозможно добавить " + searchable.getNameSearchable() + ". Список поиска полон!");
        } else {
            System.out.println(searchable.getSearchTerm());
        }
    }

    public void search(String searchString) {
        String[] listResult = new String[5];
        int i = 0;
        for (Searchable searchable : listSearch) {
            if (searchable != null && searchable.getSearchTerm().contains(searchString)) {
                listResult[i] = searchable.getStringRepresentation();
                i++;
            }
            if (i >= 5) {
                break;
            }
        }
        System.out.println("Результат поиска: '" + searchString + "'");
        System.out.println(Arrays.toString(listResult));
    }
}
