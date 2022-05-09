package de.cofinpro.sorting.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyManagerTest {

    @BeforeEach
    void setup() {
        PropertyManager.setPropertyDefaults();
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
        PropertyManager.processProperties(argList);
        assertEquals("natural", PropertyManager.getProperty(PropertyManager.SORT_OPTION));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-sortingType byCount -dataType word",
            "-dataType line -sortingType byCount"
    })
    void whenSortOptionyCount_isSortGivesByCount(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        PropertyManager.processProperties(argList);
        assertEquals("byCount", PropertyManager.getProperty(PropertyManager.SORT_OPTION));
    }

    @ParameterizedTest
    @ValueSource(strings = {"",
            "-sortingType natural",
            "-dataType word",
            "foo -dataType word bar"
    })
    void whenDataTypeEmptyOrDataTypeWord_getModeGivesWord(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        PropertyManager.processProperties(argList);
        assertEquals("word", PropertyManager.getProperty(PropertyManager.DATA_OPTION));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-dataType line",
            "foo -dataType line",
            "foo -dataType line bar"
    })
    void whenDataTypeLine_getModeGivesLine(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        PropertyManager.processProperties(argList);
        assertEquals("line", PropertyManager.getProperty(PropertyManager.DATA_OPTION));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-dataType long",
            "foo -dataType long",
            "foo -dataType long bar"
    })
    void whenDataTypeLong_getModeGivesLong(String argsString) {
        List<String> argList = Arrays.stream(argsString.split("\\s+")).toList();
        PropertyManager.processProperties(argList);
        assertEquals("long", PropertyManager.getProperty(PropertyManager.DATA_OPTION));
    }
}