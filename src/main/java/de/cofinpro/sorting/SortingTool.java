package de.cofinpro.sorting;

import de.cofinpro.sorting.controller.Mode;
import de.cofinpro.sorting.controller.SortingToolController;
import de.cofinpro.sorting.view.ConsolePrinter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SortingTool {

    private static final String USAGE = "Usage: java SortingTool [-dataType long|line|word].";

    public static void main(final String[] args) {
        if (args.length > 0 && (args.length != 2 || !"-dataType".equals(args[0]))) {
            log.error(USAGE);
            return;
        }
        Mode mode = args.length == 0 ? Mode.WORD : switch (args[1]) {
            case "long" -> Mode.LONG;
            case "line" -> Mode.LINE;
            case "word" -> Mode.WORD;
            default -> throw new IllegalStateException(USAGE);
        };
        new SortingToolController(mode, new ConsolePrinter()).run();
    }
}
