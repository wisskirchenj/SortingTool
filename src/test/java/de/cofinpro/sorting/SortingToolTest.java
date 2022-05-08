package de.cofinpro.sorting;

import de.cofinpro.sorting.controller.DataMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class SortingToolTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"",
            "-sortingType natural -dataType word",
            "-dataType line -sortingType natural",
            "-dataType long",
            "-dataType line"
    })
    void whenSortOptionEmpty_isSortGivesNatural(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertFalse(SortingTool.isSortByCount(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-sortingType byCount -dataType word",
            "-dataType line -sortingType byCount"
    })
    void whenSortOptionyCount_isSortGivesByCount(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertTrue(SortingTool.isSortByCount(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"",
            "-sortingType natural",
            "-dataType word",
            "foo -dataType word bar"
    })
    void whenDataTypeEmptyOrDataTypeWord_getModeGivesWord(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+"))
                .filter(Predicate.not(String::isEmpty)).toList();
        assertEquals(DataMode.WORD, SortingTool.getMode(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-dataType line",
            "foo -dataType line",
            "foo -dataType line bar"
    })
    void whenDataTypeLine_getModeGivesLine(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertEquals(DataMode.LINE, SortingTool.getMode(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-dataType long",
            "foo -dataType long",
            "foo -dataType long bar"
    })
    void whenDataTypeLong_getModeGivesLong(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertEquals(DataMode.LONG, SortingTool.getMode(argList));
    }


    @ParameterizedTest
    @ValueSource(strings = {"foo -dataType no",
            "foo -dataType Long bar",
            "-dataType -sortingType"
    })
    void whenArgsIncorrect_getModeThrowsIllegalState(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertThrows(IllegalStateException.class, () -> SortingTool.getMode(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-sortingType Natural",
            "-sortingType -dataType long",
    })
    void whenArgsIncorrect_isSortByCountThrowsIllegalState(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertThrows(IllegalStateException.class, () -> SortingTool.isSortByCount(argList));
    }
}