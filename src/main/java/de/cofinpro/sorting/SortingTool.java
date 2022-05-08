package de.cofinpro.sorting;

import de.cofinpro.sorting.controller.DataMode;
import de.cofinpro.sorting.controller.SortingToolController;
import de.cofinpro.sorting.view.ConsolePrinter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * main class for the sorting tool.
 */
@Slf4j
public class SortingTool {

    private static final String USAGE = "Usage: java SortingTool [-dataType long|line|word] [-sortingType natural|byCount].";
    private static final String DATA_OPTION = "-dataType";
    private static final String SORT_OPTION = "-sortingType";

    public static void main(final String[] args) {
        List<String> argList = Arrays.asList(args);
        warnSkipParameters(argList);
        new SortingToolController(getMode(argList), isSortByCount(argList), new ConsolePrinter()).run();
    }

    private static void warnSkipParameters(List<String> argList) {
        boolean isValue = false;
        for (String arg : argList) {
            if (arg.equals(SORT_OPTION) || arg.equals(DATA_OPTION)) {
                isValue = true;
                continue;
            }
            if (!isValue) {
                log.warn("\"%s\" is not a valid parameter. It will be skipped.".formatted(arg));
            }
            isValue = false;
        }
    }

    static boolean isSortByCount(List<String> argList) {
        if (!argList.contains(SORT_OPTION)) {
            return false;
        }
        if (argList.indexOf(SORT_OPTION) == argList.size() - 1) {
            log.info("No sorting type defined!");
            return false;
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
            log.info("No data type defined!");
            return DataMode.WORD;
        }
        return switch (argList.get(argList.indexOf(DATA_OPTION) + 1)) {
            case "long" -> DataMode.LONG;
            case "line" -> DataMode.LINE;
            case "word" -> DataMode.WORD;
            default -> throw new IllegalStateException(USAGE);
        };
    }
}
