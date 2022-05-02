package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.Statistics;

/**
 * classes implementing Evaluator read the user input and calculate statistical information.
 */
public interface Evaluator {

    /**
     * read the user input (presently via Scanner) and store internally.
     */
    void readUserInput();

    /**
     *
     * @return statistical information as implementation of Statistics interface
     */
    Statistics calcStatistics();
}
