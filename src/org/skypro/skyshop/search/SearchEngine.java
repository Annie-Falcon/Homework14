package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;
import org.skypro.skyshop.product.Product;

import java.util.*;

public class SearchEngine {
    private final LinkedList<Searchable> listSearch;

    public SearchEngine() {
        this.listSearch = new LinkedList<>();

    }

    public void add(Searchable searchable) {
        if (searchable != null) {
            listSearch.add(searchable);
            System.out.println(searchable.getSearchTerm());
        }
    }

    public TreeMap<String, Searchable> search(String searchString) {
        TreeMap<String, Searchable> listResult = new TreeMap<>();
        if (searchString == null || searchString.isBlank()) {
            System.out.println("Запущен поиск пустой строки!");
        } else {
            for (Searchable searchable : listSearch) {
                if (searchable != null && searchable.getSearchTerm().contains(searchString)) {
                    listResult.put(searchable.getNameSearchable(), searchable);
                }
            }
        }
        return listResult;
    }

    public Searchable searchMaxCoincidence(String searchString) throws BestResultNotFound {
        if (searchString == null || searchString.isBlank()) {
            throw new IllegalArgumentException("Запущен поиск пустой строки!");
        }
        int indexMax = -1;
        int countCoincidenceMax = 0;
        int i = 0;
        int indexBeg;
        int indexIn;
        int countCoincidence;

        for (Searchable searchable : listSearch) {
            if (searchable != null) {
                countCoincidence = 0;
                indexBeg = 0;
                indexIn = searchable.getSearchTerm().indexOf(searchString, indexBeg);
                while (indexIn != -1) {
                    countCoincidence++;
                    indexBeg = indexIn + searchString.length();
                    indexIn = searchable.getSearchTerm().indexOf(searchString, indexBeg);
                }
                if (countCoincidence > countCoincidenceMax) {
                    countCoincidenceMax = countCoincidence;
                    indexMax = i;
                }
            }
            i++;
        }
        if (indexMax == -1) {
            throw new BestResultNotFound(searchString);
        }
        return listSearch.get(indexMax);
    }


}
