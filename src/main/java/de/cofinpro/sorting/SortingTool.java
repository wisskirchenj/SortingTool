package de.cofinpro.sorting;

import de.cofinpro.sorting.controller.Mode;
import de.cofinpro.sorting.controller.SortingToolController;
import de.cofinpro.sorting.view.ConsolePrinter;

import java.util.Arrays;
import java.util.List;

/**
 * main class for the sorting tool.
 */
public class SortingTool {

    private static final String USAGE = "Usage: java SortingTool [-dataType long|line|word].";
    private static final String DATA_OPTION = "-dataType";

    public static void main(final String[] args) {
        new SortingToolController(getMode(Arrays.asList(args)), new ConsolePrinter()).run();
    }

    static Mode getMode(List<String> argList) {
        if (argList.isEmpty()) {
            return Mode.WORD;
        }
        if (argList.contains("-sortIntegers")) {
            return Mode.SORT_LONG;
        }
        if (!argList.contains(DATA_OPTION) || argList.indexOf(DATA_OPTION) >= argList.size() - 1) {
            throw new IllegalStateException(USAGE);
        }
        return switch (argList.get(argList.indexOf(DATA_OPTION) + 1)) {
            case "long" -> Mode.LONG;
            case "line" -> Mode.LINE;
            case "word" -> Mode.WORD;
            default -> throw new IllegalStateException(USAGE);
        };
    }
}
