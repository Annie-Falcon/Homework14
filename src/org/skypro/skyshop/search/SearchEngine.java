package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;
import java.util.*;
import java.util.stream.Collectors;

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
        TreeSet<Searchable> listResult = new TreeSet<>();
        if (searchString == null || searchString.isBlank()) {
            System.out.println("Запущен поиск пустой строки!");
        } else {
            listResult = listSearch.stream()
                    .filter(Objects::nonNull)
                    .filter(i -> i.getSearchTerm().contains(searchString))
                    .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt((Searchable o) -> o.getNameSearchable().length()).thenComparing(Searchable::getNameSearchable))));
        }
        return listResult;
    }

    public Searchable searchMaxCoincidence(String searchString) throws BestResultNotFound {
        if (searchString == null || searchString.isBlank()) {
            throw new IllegalArgumentException("Запущен поиск пустой строки!");
        }
        Optional<Searchable> maxSearchable = listSearch.stream()
                .filter(Objects::nonNull)
                .filter(i -> i.getSearchTerm().contains(searchString))
                .max(Comparator.comparingInt((Searchable o) ->
                        o.countCoincidence(searchString)).thenComparing(Searchable::getNameSearchable));

        if (maxSearchable.isEmpty()) {
            throw new BestResultNotFound(searchString);
        }
        return maxSearchable.get();
    }
}
