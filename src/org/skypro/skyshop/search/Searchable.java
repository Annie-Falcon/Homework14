package org.skypro.skyshop.search;

public interface Searchable {
    default String getStringRepresentation() {
        return getNameSearchable() + " - " + getTypeSearchable();
    }

    default int countCoincidence(String searchString) {
        int indexBeg;
        int indexIn;
        int countCoincidence;
        countCoincidence = 0;
        indexBeg = 0;
        indexIn = getSearchTerm().indexOf(searchString, indexBeg);
        while (indexIn != -1) {
            countCoincidence++;
            indexBeg = indexIn + searchString.length();
            indexIn = getSearchTerm().indexOf(searchString, indexBeg);
        }
        return countCoincidence;
    }

    String getSearchTerm();

    String getTypeSearchable();

    String getNameSearchable();
}
