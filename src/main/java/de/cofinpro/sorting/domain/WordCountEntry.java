package de.cofinpro.sorting.domain;

import java.util.Objects;

/**
 * Comparable-implementing class (as immutable java record) similar to a Map.Entry, to have a generic and comparable
 * CountEntry-implementing type for word-data.
 * @param key the word string as key
 * @param count the occurring frequency of the word (the key).
 */
public record WordCountEntry(String key, Long count) implements Comparable<WordCountEntry>, CountEntry {

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Long getCount() {
        return count;
    }

    @Override
    public int compareTo(WordCountEntry o) {
        return count.compareTo(o.count) == 0 ? key.compareTo(o.key)
                : count.compareTo(o.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordCountEntry that = (WordCountEntry) o;
        return Objects.equals(count, that.count) && Objects.equals(key, that.key);
    }
}
