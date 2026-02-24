package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;
import org.skypro.skyshop.product.Product;

import java.util.*;

public class SearchEngine {
    private final Set<Searchable> listSearch;

    public SearchEngine() {
        this.listSearch = new HashSet<>();
    }

    public void add(Searchable searchable) {
        if (searchable != null) {
            listSearch.add(searchable);
            System.out.println(searchable.getSearchTerm());
        }
    }

    public TreeSet<Searchable> search(String searchString) {
        TreeSet<Searchable> listResult = new TreeSet<>(new Comparator<Searchable>() {
            @Override
            public int compare(Searchable o1, Searchable o2) {
                int result = Integer.compare(o1.getNameSearchable().length(), o2.getNameSearchable().length());
                if (result == 0) {
                    result = o1.getNameSearchable().compareTo(o2.getNameSearchable());
                }
                return result;
            }
        });

        if (searchString == null || searchString.isBlank()) {
            System.out.println("Запущен поиск пустой строки!");
        } else {
            for (Searchable searchable : listSearch) {
                if (searchable != null && searchable.getSearchTerm().contains(searchString)) {
                    listResult.add(searchable);
                }
            }
        }
        return listResult;
    }

    public Searchable searchMaxCoincidence(String searchString) throws BestResultNotFound {
        if (searchString == null || searchString.isBlank()) {
            throw new IllegalArgumentException("Запущен поиск пустой строки!");
        }
        Searchable maxSearchable = null;
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
                    maxSearchable = searchable;
                }
            }
            i++;
        }
        if (maxSearchable == null) {
            throw new BestResultNotFound(searchString);
        }
        return maxSearchable;
    }


}
