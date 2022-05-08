package de.cofinpro.sorting.domain;

import java.util.Objects;

/**
 * Comparable-implementing class (as immutable java record) similar to a Map.Entry, to have a generic and comparable
 * CountEntry-implementing type for Line-data.
 * @param key the line string as key
 * @param count the occurring frequency of the line (the key).
 */
public record LineCountEntry(Line key, Long count) implements Comparable<LineCountEntry>, CountEntry {

    @Override
    public Line getKey() {
        return key;
    }

    @Override
    public Long getCount() {
        return count;
    }

    @Override
    public int compareTo(LineCountEntry o) {
        return count.compareTo(o.count) == 0 ? key.compareTo(o.key)
                : count.compareTo(o.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineCountEntry that = (LineCountEntry) o;
        return Objects.equals(count, that.count) && Objects.equals(key, that.key);
    }
}
