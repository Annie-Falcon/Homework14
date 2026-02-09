package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;

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

    public Searchable searchMaxCoincidence(String searchString) throws BestResultNotFound {
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
        return listSearch[indexMax];
    }


}
