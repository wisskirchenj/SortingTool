package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.*;
import de.cofinpro.sorting.view.ConsolePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Application controller for Sorting Tool. The run() method is the entry point for the main application.
 * The ConsolePrinter is (mock-friendly) delivered through the constructor.
 * The controller reads in the user data and also does the CountEntry grouping in case sortByCount was chosen.
 * It then uses the generic own SortingStrategies implementations - "merge Sort"-algorithm for natural sorting and
 * "insertion sort"-algorithm for byCount-sorting (just for a change).
 */
public record SortingToolController(DataMode dataMode, boolean sortByCount, ConsolePrinter consolePrinter) {

    /**
     * entry point for the Sorting Tool.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<?> sorted = switch (dataMode) {
            case LONG -> sortByCount ? SortingStrategies.insertionSort(readLongsByCount(scanner))
                    : SortingStrategies.mergeSort(readLongs(scanner));
            case LINE -> sortByCount ? SortingStrategies.insertionSort(readLinesByCount(scanner))
                    : SortingStrategies.mergeSort(readLines(scanner));
            case WORD -> sortByCount ? SortingStrategies.insertionSort(readWordsByCount(scanner))
                    : SortingStrategies.mergeSort(readWords(scanner));
        };
        consolePrinter.printSortResult(sorted);
    }

    private List<WordCountEntry> readWordsByCount(Scanner scanner) {
        return readWords(scanner).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(me -> new WordCountEntry(me.getKey(), me.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<LineCountEntry> readLinesByCount(Scanner scanner) {
        return readLines(scanner).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(me -> new LineCountEntry(me.getKey(), me.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<LongCountEntry> readLongsByCount(Scanner scanner) {
        return readLongs(scanner).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(me -> new LongCountEntry(me.getKey(), me.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<String> readWords(Scanner scanner) {
        return scanner.useDelimiter("\\s+").tokens()
                .filter(token -> !token.matches("\\s*"))
                .toList();
    }

    private List<Line> readLines(Scanner scanner) {
        return scanner.useDelimiter("\\n").tokens()
                .map(Line::new)
                .toList();
    }

    private List<Long> readLongs(Scanner scanner) {
        return scanner.useDelimiter("\\s+").tokens()
                .filter(token -> !token.matches("\\s*"))
                .map(token -> {
                    if (!token.matches("-?\\d+")) {
                        consolePrinter.print("\"%s\" is not a long. It will be skipped.".formatted(token));
                    }
                    return token;
                })
                .filter(token -> token.matches("-?\\d+"))
                .map(Long::parseLong)
                .toList();
    }
}