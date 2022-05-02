package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.LineStatistics;
import de.cofinpro.sorting.domain.Statistics;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Evaluator implementation for line-mode.
 */
public class LineEvaluator implements Evaluator {

    private List<String> lineList;
    private final Scanner scanner;

    public LineEvaluator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * reads all tokens until end-of-file and splits them into a list of lines.
     */
    @Override
    public void readUserInput() {
        lineList = scanner.useDelimiter("\\n").tokens().toList();
    }

    /**
     * calc statistics summary from input. The "maximum line" is determined by first comparing the length
     * of the line and then - if equal - the lexicographical order.
     * @return a Statistics object containing the result.
     */
    @Override
    public Statistics calcStatistics() {
        long amount = lineList.size();
        String max = lineList.stream()
                .max(Comparator.comparingLong(String::length).thenComparing(String::compareTo))
                .orElse("");
        long maxOccurrences = lineList.stream().filter(max::equals).count();
        return new LineStatistics(amount, max, maxOccurrences);
    }
}
