package de.cofinpro.sorting.domain;

/**
 * Statistics interface, that is implemented for the different modes (long, line, word, ...). Statistics
 * objects are filled by the Evaluator implementations.
 */
public interface Statistics extends Result {

    /**
     * get the amount of elements.
     * @return amount of input elements.
     */
    long getAmount();

    /**
     * get max occurrences
     * @return occurrences of the maximum of the user input.
     */
    long getMaxOccurrences();

    /**
     * calculates the percentage of occurrences of the max related to all elements.
     * @return the percentage value.
     */
    default long calcMaxPercentage() {
        return getAmount() != 0 ? 100 * getMaxOccurrences() / getAmount() : 0;
    }
}
