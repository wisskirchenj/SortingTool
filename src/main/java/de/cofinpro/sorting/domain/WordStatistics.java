package de.cofinpro.sorting.domain;

import lombok.Builder;
import lombok.Value;

/**
 * Immutable Statistics implementation for word-mode. Only the result string generation is specific.
 */
@Value
public class WordStatistics implements Statistics {

    long amount;
    String max;
    long maxOccurrences;


    @Override
    public String getResult() {
        return String.format("Total words: %d.%nThe longest word: %s (%d time(s), %d%%).",
                amount, max, maxOccurrences, calcMaxPercentage());
    }
}
