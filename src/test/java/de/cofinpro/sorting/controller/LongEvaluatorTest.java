package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.controller.LongEvaluator;
import de.cofinpro.sorting.domain.LongStatistics;
import de.cofinpro.sorting.domain.Statistics;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LongEvaluatorTest {

    @Mock
    Scanner scanner;

    @InjectMocks
    LongEvaluator longEvaluator;

    static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of("1 2 3\n4 5\n6\n\n7 8 9\n", 9, 9L, 1),
                Arguments.of("\n2 0  4 9 6 9 8 9 7  ", 9, 9L, 3),
                Arguments.of("1",1 ,1L, 1),
                Arguments.of("", 0, 0L, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void getNumberList(String text, int expectedSize, long expectedMax, int expectedMaxOccurrences) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\s+").tokens()).thenReturn(Arrays.stream(text.split("\\s+")));
        longEvaluator.readUserInput();
        Statistics expected = new LongStatistics(expectedSize, expectedMax, expectedMaxOccurrences);
        assertEquals(expected, longEvaluator.calcStatistics());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "2\nf", "11111111111111111333333 2", "2, 3", "2.0", "2 3 5\n 3 d"})
    void whenNonIntegerNumbers_ThenThrows(String text) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\s+").tokens()).thenReturn(Arrays.stream(text.split("\\s+")));
        assertThrows(NumberFormatException.class, () -> longEvaluator.readUserInput());
    }
}