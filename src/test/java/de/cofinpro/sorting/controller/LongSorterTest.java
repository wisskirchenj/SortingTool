package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.LongSortResult;
import de.cofinpro.sorting.domain.LongStatistics;
import de.cofinpro.sorting.domain.SortResult;
import de.cofinpro.sorting.domain.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class LongSorterTest {

    @Mock
    Scanner scanner;

    @InjectMocks
    LongSorter longSorter;

    static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of("1 3 2\n4 5\n6\n\n7 8 6\n", 9, "1 2 3 4 5 6 6 7 8"),
                Arguments.of("\n2 0  4 9 6 9 8 9 7  ", 9, "0 2 4 6 7 8 9 9 9"),
                Arguments.of("1", 1 ,"1"),
                Arguments.of("", 0, "")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void getNumberList(String text, int expectedSize, String expectedSorted) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\s+").tokens()).thenReturn(Arrays.stream(text.split("\\s+")));
        longSorter.readUserInput();
        SortResult expected = new LongSortResult(expectedSize, expectedSorted);
        assertEquals(expected, longSorter.evaluate());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "2\nf", "11111111111111111333333 2", "2, 3", "2.0", "2 3 5\n 3 d"})
    void whenNonIntegerNumbers_ThenThrows(String text) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\s+").tokens()).thenReturn(Arrays.stream(text.split("\\s+")));
        assertThrows(NumberFormatException.class, () -> longSorter.readUserInput());
    }
}