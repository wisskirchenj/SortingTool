package de.cofinpro.sorting;

import de.cofinpro.sorting.config.PropertyManager;
import de.cofinpro.sorting.controller.SortingToolController;
import de.cofinpro.sorting.view.Printer;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * main class for the sorting tool.
 */
@Slf4j
public class SortingTool {

    public static void main(final String[] args) {
        PropertyManager.processProperties(Arrays.asList(args));
        log.trace(PropertyManager.getProperties().toString());
        new SortingToolController(new Printer()).run();
    }
}
