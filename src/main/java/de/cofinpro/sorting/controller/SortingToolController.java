package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.Result;
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
        Scanner scanner = new Scanner(System.in);
        this.evaluator = switch (mode) {
            case LONG -> new LongEvaluator(scanner);
            case LINE -> new LineEvaluator(scanner);
            case WORD -> new WordEvaluator(scanner);
            case SORT_LONG -> new LongSorter(scanner);
        };
    }

    /**
     * entry point for the Sorting Tool.
     */
    public void run() {
        evaluator.readUserInput();
        Result evaluationResult = evaluator.evaluate();
        consolePrinter.printObject(evaluationResult.getResult());
    }
}
