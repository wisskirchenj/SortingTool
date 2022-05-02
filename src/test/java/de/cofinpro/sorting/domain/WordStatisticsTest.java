package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordStatisticsTest {

    WordStatistics wordStatistics;

    @BeforeEach
    void setUp() {
        wordStatistics = new WordStatistics(6, "laaang", 2);
    }

    @Test
    void testGetResult() {
        assertEquals(String.format("Total words: %d.%nThe longest word: %s (%d time(s), %d%%).", 6, "laaang", 2, 33),
                wordStatistics.getResult());
    }
}