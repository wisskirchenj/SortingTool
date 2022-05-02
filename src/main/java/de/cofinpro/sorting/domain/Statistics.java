package de.cofinpro.sorting.domain;

import java.io.Serializable;

/**
 * Statistics interface, that is implemented for the different modes (long, line, word, ...). Statistics
 * objects are filled by the Evaluator implementations.
 */
public interface Statistics extends Serializable {

    /**
     * get the amount of elements.
     * @return amount of input elements.
     */
    long getAmount();

    /**
     * get max occurrences
     * @return occurences of the maximum of the user input.
     */
    long getMaxOccurrences();

    /**
     * return a implementation specific result summary for this Statistics.
     * @return result summary as String
     */
    String getResult();

    /**
     * calculates the percentage of occurrences of the max related to all elements.
     * @return the percentage value.
     */
    default long calcMaxPercentage() {
        return getAmount() != 0 ? 100 * getMaxOccurrences() / getAmount() : 0;
    }
}
