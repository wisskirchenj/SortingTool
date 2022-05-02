package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.LongStatistics;
import de.cofinpro.sorting.domain.Statistics;

import java.util.List;
import java.util.Scanner;

/**
 * Evaluator implementation for long-mode.
 */
public class LongEvaluator implements Evaluator {

    private final Scanner scanner;
    private List<Long> longList;

    public LongEvaluator(Scanner scanner) {
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
                .toList();
    }

    /**
     * evaluates the list of numbers given for amount, max and maxOccurrences
     * @return the described information wrapped into a LongStatistics DTO
     */
    @Override
    public Statistics evaluate() {
        long amount = longList.size();
        long max = longList.stream().max(Long::compareTo).orElse(0L);
        long maxOccurrences = longList.stream().filter(n -> n == max).count();
        return new LongStatistics(amount, max, maxOccurrences);
    }
}
