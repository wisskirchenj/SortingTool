package de.cofinpro.sorting.view;

import de.cofinpro.sorting.config.PropertyManager;
import de.cofinpro.sorting.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Console printer with one public method printSortResult, that gets the sorted list of a generic type and
 * formats the output, depending on the generic contents of the list.
 * uses Log4j for printing to stdout (not in the hyperskill version).
 */
@Slf4j
public class Printer {

    private FileWriter writer = null;

    public Printer() {
        initLogger();
    }

    private void initLogger() {
        String outputPath = PropertyManager.getProperty(PropertyManager.OUTPUT_OPTION);
        if (!outputPath.isEmpty()) {
            try {
                writer = new FileWriter(outputPath);
            } catch (IOException exception) {
                log.error("IO Error opening output file given (%s)! Writing to stdout".formatted(outputPath));
                writer = null;
            }
        }
    }

    private void log(String msg) {
        if (writer != null) {
            try {
                writer.write(msg);
            } catch (IOException e) {
                log.error("IO Error writing to output file! Writing to stdout from now on");
                log.info(msg);
                writer = null;
            }
        } else {
            log.info(msg);
        }
    }

    public void printError(String msg) {
        log.error(msg);
    }

    public void printSortResult(List<?> sorted) {
        if (sorted.get(0) instanceof Long) {
            printLongResult(sorted);
        }
        if (sorted.get(0) instanceof String) {
            printWordResult(sorted);
        }
        if (sorted.get(0) instanceof Line) {
            printLineResult(sorted);
        }
        if (sorted.get(0) instanceof LongCountEntry) {
            printLongByCountResult(sorted);
        }
        if (sorted.get(0) instanceof WordCountEntry) {
            printWordByCountResult(sorted);
        }
        if (sorted.get(0) instanceof LineCountEntry) {
            printLineByCountResult(sorted);
        }
        closeWriter();
    }

    private void closeWriter() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                log.error("IO Error closing output file!");
            }
        }
    }

    private void printWordByCountResult(List<?> sorted) {
        long amount = sorted.stream().map(e -> ((CountEntry) e).getCount()).mapToLong(Long::longValue).sum();
        log(String.format("Total words: %d.%n%s", amount, joinByCount(sorted, amount)));
    }

    private void printLineByCountResult(List<?> sorted) {
        long amount = sorted.stream().map(e -> ((CountEntry) e).getCount()).mapToLong(Long::longValue).sum();
        log(String.format("Total lines: %d.%n%s", amount, joinByCount(sorted, amount)));
    }

    private void printLongByCountResult(List<?> sorted) {
        long amount = sorted.stream().map(e -> ((CountEntry) e).getCount()).mapToLong(Long::longValue).sum();
        log(String.format("Total numbers: %d.%n%s", amount, joinByCount(sorted, amount)));
    }

    private String joinByCount(List<?> sorted, long amount) {
        StringBuilder stringBuilder = new StringBuilder();
        sorted.stream()
                .map(CountEntry.class::cast)
                .map(e -> String.format("%s: %d time(s), %d%%%n", e.getKey(), e.getCount(), 100 * e.getCount() / amount))
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private void printLineResult(List<?> sorted) {
        log(String.format("Total lines: %d.%nSorted data:%n%s", sorted.size(), joinList(sorted, "\n")));
    }

    private void printWordResult(List<?> sorted) {
        log(String.format("Total words: %d.%nSorted data: %s", sorted.size(), joinList(sorted, " ")));
    }

    private void printLongResult(List<?> sorted) {
        log(String.format("Total numbers: %d.%nSorted data: %s", sorted.size(), joinList(sorted, " ")));
    }

    private String joinList(List<?> sorted, String delimiter) {
        return sorted.stream().map(e -> e + delimiter).collect(Collectors.joining()).trim();
    }
}