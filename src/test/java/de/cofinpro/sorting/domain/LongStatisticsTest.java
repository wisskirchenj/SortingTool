package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongStatisticsTest {

    LongStatistics longStatistics;

    @BeforeEach
    void setUp() {
        longStatistics = new LongStatistics(2, 3L, 1);
    }

    @Test
    void testGetResult() {
        assertEquals(String.format("Total numbers: %d.%nThe greatest number: %d (%d time(s), %d%%).", 2, 3, 1, 50),
                longStatistics.getResult());
    }

    @Test
    void calcMaxPercentage() {
        assertEquals(50, longStatistics.calcMaxPercentage());
    }
}