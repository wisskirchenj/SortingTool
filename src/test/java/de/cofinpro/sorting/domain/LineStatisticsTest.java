package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineStatisticsTest {

    LineStatistics lineStatistics;

    @BeforeEach
    void setUp() {
        lineStatistics = new LineStatistics(3, "laaang", 1);
    }

    @Test
    void testGetResult() {
        assertEquals(String.format("Total lines: %d.%nThe longest line:%n%s%n(%d time(s), %d%%).", 3, "laaang", 1, 33),
                lineStatistics.getResult());
    }
}