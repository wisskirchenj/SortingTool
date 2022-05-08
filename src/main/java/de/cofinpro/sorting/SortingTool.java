package de.cofinpro.sorting;

import de.cofinpro.sorting.controller.DataMode;
import de.cofinpro.sorting.controller.SortingToolController;
import de.cofinpro.sorting.view.ConsolePrinter;

import java.util.Arrays;
import java.util.List;

/**
 * main class for the sorting tool.
 */
public class SortingTool {

    private static final String USAGE = "Usage: java SortingTool [-dataType long|line|word] [-sortingType natural|byCount].";
    private static final String DATA_OPTION = "-dataType";
    private static final String SORT_OPTION = "-sortingType";

    public static void main(final String[] args) {
        List<String> argList = Arrays.asList(args);
        new SortingToolController(getMode(argList), isSortByCount(argList), new ConsolePrinter()).run();
    }

    static boolean isSortByCount(List<String> argList) {
        if (!argList.contains(SORT_OPTION)) {
            return false;
        }
        if (argList.indexOf(SORT_OPTION) == argList.size() - 1) {
            throw new IllegalStateException(USAGE);
        }
        return switch (argList.get(argList.indexOf(SORT_OPTION) + 1)) {
            case "byCount" -> true;
            case "natural" -> false;
            default -> throw new IllegalStateException(USAGE);
        };
    }

    static DataMode getMode(List<String> argList) {
        if (!argList.contains(DATA_OPTION)) {
            return DataMode.WORD;
        }
        if (argList.indexOf(DATA_OPTION) == argList.size() - 1) {
            throw new IllegalStateException(USAGE);
        }
        return switch (argList.get(argList.indexOf(DATA_OPTION) + 1)) {
            case "long" -> DataMode.LONG;
            case "line" -> DataMode.LINE;
            case "word" -> DataMode.WORD;
            default -> throw new IllegalStateException(USAGE);
        };
    }
}
