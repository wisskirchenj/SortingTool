package de.cofinpro.sorting.domain;

import lombok.Value;

/**
 * Immutable Statistics implementation for long-mode. Only the result string generation is specific.
 */
@Value
public class LongStatistics implements Statistics {

    long amount;
    Long max;
    long maxOccurrences;

    @Override
    public String getResult() {
        return String.format("Total numbers: %d.%nThe greatest number: %d (%d time(s), %d%%).",
                amount, max, maxOccurrences, calcMaxPercentage());
    }
}
