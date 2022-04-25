package de.cofinpro.sorting.domain;

import java.util.List;

/**
 * class, whose instances analyze an arbitrarily long list of Long numbers and return
 * statistical information.
 */
public class NumberEvaluator {

    /**
     * evaluates the list of numbers given for amount, max and maxOccurrences
     * @param numbers an arbitrary list of numbers
     * @return the described information wrapped into a NumberStatisticsDTO
     */
    public NumberStatistics calcStatistics(List<Long> numbers) {
        long amount = numbers.size();
        long max = numbers.stream().max(Long::compareTo).orElse(0L);
        long maxOccurrences = numbers.stream().filter(n -> n == max).count();
        return new NumberStatistics(amount, max, maxOccurrences);
    }
}
