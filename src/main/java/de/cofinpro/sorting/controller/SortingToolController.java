package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.config.PropertyManager;
import de.cofinpro.sorting.domain.*;
import de.cofinpro.sorting.view.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Application controller for Sorting Tool. The run() method is the entry point for the main application.
 * The Printer is (mock-friendly) delivered through the constructor.
 * The controller reads in the user data and also does the CountEntry grouping in case sortByCount was chosen.
 * It then uses the generic own SortingStrategies implementations - "merge Sort"-algorithm for natural sorting and
 * "insertion sort"-algorithm for byCount-sorting (just for a change).
 */
public record SortingToolController(Printer printer) {

    /**
     * entry point for the Sorting Tool.
     */
    public void run() {
        boolean sortByCount = PropertyManager.getProperty(PropertyManager.SORT_OPTION).equals("byCount");
        Scanner scanner = setScanner();
        List<?> sorted = switch (PropertyManager.getProperty(PropertyManager.DATA_OPTION)) {
            case "long" -> sortByCount ? SortingStrategies.insertionSort(readLongsByCount(scanner))
                    : SortingStrategies.mergeSort(readLongs(scanner));
            case "line" -> sortByCount ? SortingStrategies.insertionSort(readLinesByCount(scanner))
                    : SortingStrategies.mergeSort(readLines(scanner));
            case "word" -> sortByCount ? SortingStrategies.insertionSort(readWordsByCount(scanner))
                    : SortingStrategies.mergeSort(readWords(scanner));
            default -> throw new RuntimeException("internal error");
        };
        printer.printSortResult(sorted);
    }

    private Scanner setScanner() {
        String inputFilePath = PropertyManager.getProperty(PropertyManager.INPUT_OPTION);
        if (!inputFilePath.isEmpty()) {
            try {
                return new Scanner(new File(inputFilePath));
            } catch (FileNotFoundException exception) {
                printer.printError("Input file given (%s) not found. Reading from stdin".formatted(inputFilePath));
            }
        }
        return new Scanner(System.in);
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
                        printer.printError("\"%s\" is not a long. It will be skipped.".formatted(token));
                    }
                    return token;
                })
                .filter(token -> token.matches("-?\\d+"))
                .map(Long::parseLong)
                .toList();
    }
}