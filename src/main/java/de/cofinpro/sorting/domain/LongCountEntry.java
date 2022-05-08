package de.cofinpro.sorting.domain;

import java.util.Objects;

/**
 * Comparable-implementing class (as immutable java record) similar to a Map.Entry, to have a generic and comparable
 * CountEntry-implementing type for Long-data.
 * @param key the Long number as key
 * @param count the occurring frequency of the number (the key).
 */
public record LongCountEntry(Long key, Long count) implements Comparable<LongCountEntry>, CountEntry {

    @Override
    public Long getKey() {
        return key;
    }

    @Override
    public Long getCount() {
        return count;
    }

    @Override
    public int compareTo(LongCountEntry o) {
        return count.compareTo(o.count) == 0 ? key.compareTo(o.key)
                : count.compareTo(o.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongCountEntry that = (LongCountEntry) o;
        return Objects.equals(count, that.count) && Objects.equals(key, that.key);
    }
}
