package de.cofinpro.sorting.controller;

import de.cofinpro.sorting.domain.LongStatistics;
import de.cofinpro.sorting.view.ConsolePrinter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
    void wheModeLong_ThenLongStatistics() {
        controller = new SortingToolController(Mode.LONG, consolePrinter);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("number:"));
    }

    @Test
    void wheModeLine_ThenLineStatistics() {
        controller = new SortingToolController(Mode.LINE, consolePrinter);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("line:"));
    }

    @Test
    void wheModeWord_ThenLongStatistics() {
        controller = new SortingToolController(Mode.WORD, consolePrinter);
        controller.run();
        verify(consolePrinter).printObject(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("word:"));
    }
}