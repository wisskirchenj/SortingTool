package de.cofinpro.sorting.domain;

/**
 * SortResult interface, that is implemented for different modes (long, line, word, ...). These
 * objects are produced by the Sorter (Evaluator) implementations.
 */
public interface SortResult extends Result {

    /**
     * get the amount of elements.
     * @return amount of input elements.
     */
    long getAmount();

    /**
     * get the sorted elements as StringRepresentation.
     * @return sorted elements as string.
     */
    String getSorted();
}