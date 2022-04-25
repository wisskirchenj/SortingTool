package de.cofinpro.sorting;

import de.cofinpro.sorting.controller.SortingToolController;
import de.cofinpro.sorting.view.ConsolePrinter;
import de.cofinpro.sorting.view.NumberReader;

import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        new SortingToolController(new NumberReader(new Scanner(System.in)),
                new ConsolePrinter()).run();
    }
}
