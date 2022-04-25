package de.cofinpro.sorting.view;

import java.util.List;
import java.util.Scanner;

/**
 * view class (user interaction) for getting user's number input via stdin.
 */
public class NumberReader {

    private final Scanner scanner;

    public NumberReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Read a whitespace-separated list of long integers form stdin until end-of-file (Cmd-D on keyboard) and return
     * a tokenized list of Long numbers. Empty strings are filtered (e.g. around linebreak)
     * @return List of Long numbers.
     * @throws NumberFormatException if a non-integer token is entered.
     */
    public List<Long> getNumberList() {
        return scanner.useDelimiter("\\s+").tokens().filter(token -> !token.matches("\\s*"))
                .map(Long::parseLong)
                .toList();
    }
}
