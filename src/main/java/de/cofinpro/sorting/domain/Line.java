package de.cofinpro.sorting.domain;

import java.util.Objects;

/**
 * wrapper class (immutable java record) around String, to have a generic and comparable Line type.
 * @param value the line string
 */
public record Line(String value) implements Comparable<Line> {

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(Line o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(value, line.value);
    }
}