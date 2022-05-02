package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.Statistics;
import de.cofinpro.sorting.domain.WordStatistics;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Evaluator implementation for word-mode.
 */
public class WordEvaluator implements Evaluator {

    private final Scanner scanner;
    private List<String> wordList;

    public WordEvaluator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Read a whitespace-separated list of words form stdin until end-of-file (Cmd-D on keyboard) and return
     * a tokenized list. Empty strings are filtered (e.g. around linebreak)
     */
    @Override
    public void readUserInput() {
        wordList = scanner.useDelimiter("\\s+").tokens()
                .filter(token -> !token.matches("\\s*"))
                .toList();
    }

    /**
     * calc statistics summary from input. The "maximum word" is determined by first comparing the length
     * of the word and then - if equal - the lexicographical order.
     * @return a Statistics object containing the result.
     */
    @Override
    public Statistics calcStatistics() {
        long amount = wordList.size();
        String max = wordList.stream()
                .max(Comparator.comparingLong(String::length).thenComparing(String::compareTo))
                .orElse("");
        long maxOccurrences = wordList.stream().filter(max::equals).count();
        return new WordStatistics(amount, max, maxOccurrences);
    }
}
