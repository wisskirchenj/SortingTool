package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.view.ConsolePrinter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SortingToolControllerTest {

    @Mock
    ConsolePrinter consolePrinter;

    SortingToolController controller;

    @Captor
    ArgumentCaptor<String> printCaptor;

    @Test
    void whenModeLong_ThenLongStatistics() {
        controller = new SortingToolController(Mode.LONG, consolePrinter);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("number:"));
    }

    @Test
    void whenModeLine_ThenLineStatistics() {
        controller = new SortingToolController(Mode.LINE, consolePrinter);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("line:"));
    }

    @Test
    void whenModeWord_ThenLongStatistics() {
        controller = new SortingToolController(Mode.WORD, consolePrinter);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("word:"));
    }

    @Test
    void whenModeSortLong_ThenLongSortResult() {
        controller = new SortingToolController(Mode.SORT_LONG, consolePrinter);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("numbers:"));
        assertTrue(printCaptor.getValue().contains("Sorted data:"));
    }
}