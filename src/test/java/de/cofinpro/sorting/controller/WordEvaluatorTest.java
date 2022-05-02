package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.Statistics;
import de.cofinpro.sorting.domain.WordStatistics;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WordEvaluatorTest {
    @Mock
    Scanner scanner;

    @InjectMocks
    WordEvaluator wordEvaluator;

    static Stream<Arguments> provideLines() {
        return Stream.of(
                Arguments.of("1 2 3\n4 5\n6\n\n7 8 9\n", 9, "9", 1),
                Arguments.of("1 2 3\n4 5\n6\nlang\n7 8 9\n", 10, "lang", 1),
                Arguments.of("\n2 0  4 9 6 9 8 9 7  ", 9, "9", 3),
                Arguments.of("1",1 ,"1", 1),
                Arguments.of("", 0, "", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLines")
    void getNumberList(String text, int expectedSize, String expectedMax, int expectedMaxOccurrences) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\s+").tokens()).thenReturn(Arrays.stream(text.split("\\s+")));
        wordEvaluator.readUserInput();
        Statistics expected = new WordStatistics(expectedSize, expectedMax, expectedMaxOccurrences);
        assertEquals(expected, wordEvaluator.evaluate());
    }
}
