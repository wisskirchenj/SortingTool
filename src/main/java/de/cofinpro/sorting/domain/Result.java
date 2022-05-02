package de.cofinpro.sorting.domain;

import java.io.Serializable;

/**
 * Base interface for Statistics and SortResult as produced by Evaluator's.
 */
public interface Result extends Serializable {

    /**
     * get a result summary to display.
     * @return result summary as String.
     */
    String getResult();
}
