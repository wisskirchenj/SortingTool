package de.cofinpro.sorting.domain;

import lombok.Value;

@Value
public class NumberStatistics {
    long amount;
    long max;
    long maxOccurrences;

    @Override
    public String toString() {
        return String.format("Total numbers: %d.%nThe greatest number: %d (%d time(s)).",
                amount, max, maxOccurrences);
    }
}
