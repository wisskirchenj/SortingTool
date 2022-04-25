package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumberEvaluatorTest {

    NumberEvaluator numberEvaluator;

    @BeforeEach
    void setUp() {
        numberEvaluator = new NumberEvaluator();
    }

    static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(List.of(), 0, 0, 0),
                Arguments.of(List.of(2L), 1, 2, 1),
                Arguments.of(List.of(-1L, 0L, 1L), 3 ,1, 1),
                Arguments.of(List.of(-1L, -1L, -1L), 3 ,-1, 3),
                Arguments.of(List.of(1L, -1L, 0L, -1L, -1L, 1L, 0L), 7 ,1, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void calcStatistics(List<Long> numbers, int expectedAmount, int expectedMax, int expectedMaxOccurrences) {
        NumberStatistics stat = numberEvaluator.calcStatistics(numbers);
        assertEquals(new NumberStatistics(expectedAmount, expectedMax, expectedMaxOccurrences), stat);
    }
}