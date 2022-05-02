package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongSortResultTest {

    LongSortResult longSortResult;

    @BeforeEach
    void setUp() {
        longSortResult = new LongSortResult(2, "1 2 3 4");
    }

    @Test
    void getResult() {
        assertEquals(String.format("Total numbers: %d.%nSorted data: %s", 2, "1 2 3 4"),
                longSortResult.getResult());
    }
}