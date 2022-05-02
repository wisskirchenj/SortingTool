package de.cofinpro.sorting.domain;

import lombok.Value;

/**
 * Immutable Statistics implementation for line-mode. Only the result string generation is specific.
 */
@Value
public class LineStatistics implements Statistics {
    long amount;
    String max;
    long maxOccurrences;

    @Override
    public String getResult() {
        return String.format("Total lines: %d.%nThe longest line:%n%s%n(%d time(s), %d%%).",
                amount, max, maxOccurrences, calcMaxPercentage());
    }
}
