package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.Statistics;
import de.cofinpro.sorting.view.ConsolePrinter;

import java.util.Scanner;

/**
 * Application controller for Sorting Tool. The run() method is the entry point for the main application.
 * The ConsolePrinter is (mock-friendly) delivered through the constructor. The mode enum decides, which
 * implementation of Evaluator is used in the app.
 */
public class SortingToolController {

    private final Evaluator evaluator;
    private final ConsolePrinter consolePrinter;

    public SortingToolController(Mode mode, ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
        this.evaluator = switch (mode) {
            case LONG -> new LongEvaluator(new Scanner(System.in));
            case LINE -> new LineEvaluator(new Scanner(System.in));
            case WORD -> new WordEvaluator(new Scanner(System.in));
        };
    }

    /**
     * entry point for the Sorting Tool.
     */
    public void run() {
        evaluator.readUserInput();
        Statistics statistics = evaluator.calcStatistics();
        consolePrinter.printObject(statistics.getResult());
    }
}
