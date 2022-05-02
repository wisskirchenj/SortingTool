package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.LongSortResult;
import de.cofinpro.sorting.domain.SortResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Evaluator implementation for reading Longs from stdin, sorting and returning the result.
 */
public class LongSorter implements Evaluator {

    private final Scanner scanner;
    private List<Long> longList;

    public LongSorter(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Read a whitespace-separated list of long integers from stdin until end-of-file (Cmd-D on keyboard) and return
     * a tokenized list of Long numbers. Empty strings are filtered (e.g. around linebreak)
     * @throws NumberFormatException if a non-integer token is entered.
     */
    @Override
    public void readUserInput() {
        longList = scanner.useDelimiter("\\s+").tokens()
                .filter(token -> !token.matches("\\s*"))
                .map(Long::parseLong)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * sorts the list and creates / returns a SortResult object with #elements and a string representation of the
     * sorted elements
     * @return results of the Sorting as SortResult.
     */
    @Override
    public SortResult evaluate() {
        Collections.sort(longList);
        return new LongSortResult(longList.size(),
                longList.stream().map(l -> l + " ").collect(Collectors.joining()).trim());
    }
}
