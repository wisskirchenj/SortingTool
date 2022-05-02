package de.cofinpro.sorting.domain;

import lombok.Value;

/**
 * Immutable SortResult implementation for long-mode.
 */
@Value
public class LongSortResult implements SortResult {

    long amount;
    String sorted;

    @Override
    public String getResult() {
        return String.format("Total numbers: %d.%nSorted data: %s", amount, sorted);
    }
}
