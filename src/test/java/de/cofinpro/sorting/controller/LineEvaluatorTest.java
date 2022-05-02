package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.LineStatistics;
import de.cofinpro.sorting.domain.Statistics;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
class LineEvaluatorTest {

    @Mock
    Scanner scanner;

    @InjectMocks
    LineEvaluator lineEvaluator;

    static Stream<Arguments> provideLines() {
        return Stream.of(
                Arguments.of("1 2 3\n4 5\n6\n\n7 8 9\n", 5, "7 8 9", 1),
                Arguments.of("\n2 0  4 9 6 9 8 9 7  ", 2, "2 0  4 9 6 9 8 9 7  ", 1),
                Arguments.of("1",1 ,"1", 1),
                Arguments.of("", 1, "", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLines")
    void getNumberList(String text, int expectedSize, String expectedMax, int expectedMaxOccurrences) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\n").tokens()).thenReturn(Arrays.stream(text.split("\\n")));
        lineEvaluator.readUserInput();
        Statistics expected = new LineStatistics(expectedSize, expectedMax, expectedMaxOccurrences);
        assertEquals(expected, lineEvaluator.calcStatistics());
    }
}