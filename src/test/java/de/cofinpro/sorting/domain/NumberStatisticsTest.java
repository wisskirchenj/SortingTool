package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberStatisticsTest {

    NumberStatistics numberStatistics;

    @BeforeEach
    void setUp() {
        numberStatistics = new NumberStatistics(2, 3, 1);
    }

    @Test
    void testToString() {
        assertEquals(String.format("Total numbers: %d.%nThe greatest number: %d (%d time(s)).", 2, 3, 1),
                numberStatistics.toString());

    }
}