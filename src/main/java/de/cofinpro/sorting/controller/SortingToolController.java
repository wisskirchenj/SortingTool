package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.NumberEvaluator;
import de.cofinpro.sorting.domain.NumberStatistics;
import de.cofinpro.sorting.view.ConsolePrinter;
import de.cofinpro.sorting.view.NumberReader;

import java.util.List;

/**
 * Application controller for Sorting Tool. The run() method is the entry point for the main application.
 * The IO-Helper instances of NumberReader and ConsolePrinter are (mock-friendly) delivered through the constructor.
 */
public class SortingToolController {

    private final NumberReader numberReader;
    private final ConsolePrinter consolePrinter;

    public SortingToolController(NumberReader numberReader, ConsolePrinter consolePrinter) {
        this.numberReader = numberReader;
        this.consolePrinter = consolePrinter;
    }

    /**
     * entry point for the Sorting Tool.
     */
    public void run() {
        List<Long> numbers = numberReader.getNumberList();
        NumberStatistics statistics = new NumberEvaluator().calcStatistics(numbers);
        consolePrinter.printObject(statistics);
    }
}
