package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    Line line;
    static String someString = "some line";

    @BeforeEach
    void setup() {
        line = new Line(someString);
    }

    @Test
    void testToString() {
        assertEquals(someString, line.toString());
    }

    @Test
    void compareTo() {
        String smaller = "some";
        assertTrue(line.compareTo(new Line(smaller)) > 0);
        String bigger = "t";
        assertFalse(line.compareTo(new Line(bigger)) >= 0);
    }
}