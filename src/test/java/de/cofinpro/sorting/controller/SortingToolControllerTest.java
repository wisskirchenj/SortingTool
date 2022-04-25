package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.NumberStatistics;
import de.cofinpro.sorting.view.ConsolePrinter;
import de.cofinpro.sorting.view.NumberReader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SortingToolControllerTest {

    @Mock
    NumberReader numberReader;

    @Mock
    ConsolePrinter consolePrinter;

    @InjectMocks
    SortingToolController controller;

    @Captor
    ArgumentCaptor<NumberStatistics> printCaptor;

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
    void whenRun_ThenStatisticsCorrect(List<Long> numbers, int expectedAmount, int expectedMax, int expectedMaxOccurrences) {
        when(numberReader.getNumberList()).thenReturn(numbers);
        NumberStatistics expected = new NumberStatistics(expectedAmount, expectedMax, expectedMaxOccurrences);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertEquals(expected, printCaptor.getValue());
    }
}