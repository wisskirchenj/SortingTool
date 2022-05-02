package de.cofinpro.sorting;

import de.cofinpro.sorting.controller.Mode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    @ValueSource(strings = {"-sortIntegers",
            "-sortIntegers -dataType word",
            "-sortIntegers -dataType long",
            "-dataType foo -sortIntegers",
            "-dataType line -sortIntegers"
    })
    void whenSortIntegersOption_getModeGivesSort(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertEquals(Mode.SORT_LONG, SortingTool.getMode(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"",
            "-dataType word",
            "foo -dataType word bar"
    })
    void whenArgsEmptyOrDataTypeWord_getModeGivesWord(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+"))
                .filter(Predicate.not(String::isEmpty)).toList();
        assertEquals(Mode.WORD, SortingTool.getMode(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-dataType line",
            "foo -dataType line",
            "foo -dataType line bar"
    })
    void whenDataTypeLine_getModeGivesLine(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertEquals(Mode.LINE, SortingTool.getMode(argList));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-dataType long",
            "foo -dataType long",
            "foo -dataType long bar"
    })
    void whenDataTypeLong_getModeGivesLong(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertEquals(Mode.LONG, SortingTool.getMode(argList));
    }


    @ParameterizedTest
    @ValueSource(strings = {"-dataType",
            "foo -dataType no",
            "foo -dataType Long bar",
            "foo",
            "long -dataType"
    })
    void whenArgsIncorrect_getModeThrowsIllegalState(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        assertThrows(IllegalStateException.class, () -> SortingTool.getMode(argList));
    }
}