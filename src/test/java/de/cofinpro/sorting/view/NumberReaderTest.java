package de.cofinpro.sorting.view;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NumberReaderTest {

    @Mock
    Scanner scanner;

    @InjectMocks
    NumberReader numberReader;

    static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of("1 2 3\n4 5\n6\n\n7 8 9\n", 9, 1),
                        Arguments.of("\n2 0  4 5 6 7 8 9 7  ", 9, 2),
                        Arguments.of("1",1 ,1),
                        Arguments.of("", 0, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void getNumberList(String text, int expectedSize, int expectedFirst) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\s+").tokens()).thenReturn(Arrays.stream(text.split("\\s+")));
        List<Long> numbers = numberReader.getNumberList();
        assertNotNull(numbers);
        assertEquals(expectedSize, numbers.size());
        if (numbers.size()== 0) return;
        assertEquals(expectedFirst, numbers.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "2\nf", "11111111111111111333333 2", "2, 3", "2.0", "2 3 5\n 3 d"})
    void whenNonIntegerNumbers_ThenThrows(String text) {
        when(scanner.useDelimiter(anyString())).thenReturn(scanner);
        when(scanner.useDelimiter("\\s+").tokens()).thenReturn(Arrays.stream(text.split("\\s+")));
        assertThrows(NumberFormatException.class, () -> numberReader.getNumberList());
    }
}