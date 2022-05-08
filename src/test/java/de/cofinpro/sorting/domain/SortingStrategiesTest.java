package de.cofinpro.sorting.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SortingStrategiesTest {

    @BeforeEach
    void setUp() {
    }

    static Stream<Arguments> listProvide() {

        return Stream.of(
                Arguments.of(new ArrayList<>(List.of())),
                Arguments.of(new ArrayList<>(List.of(3))),
                Arguments.of(new ArrayList<>(List.of(2, 4))),
                Arguments.of(new ArrayList<>(List.of(4, 2))),
                Arguments.of(new ArrayList<>(List.of(3, 1, 4, 2))),
                Arguments.of(new ArrayList<>(List.of(3L, -1L, 4L, 2L, 12L))),
                Arguments.of(new ArrayList<>(List.of("as", "a", "ABC", "AA", "A", "as", "a", "ABC", "AA", "A", "as", "a", "ABC", "AA", "A"))),
                Arguments.of(new ArrayList<>(List.of("a s", "a ", "Aa", "aa", "A ")))
        );
    }

    @ParameterizedTest
    @MethodSource("listProvide")
    <T extends Comparable<T>> void insertionSort(List<T> listToSort) {
        List<T> expected = new ArrayList<>(listToSort);
        Collections.sort(expected);
        assertEquals(expected, SortingStrategies.insertionSort(listToSort));
    }


    @ParameterizedTest
    @MethodSource("listProvide")
    <T extends Comparable<T>> void mergeSort(List<T> listToSort) {
        List<T> expected = new ArrayList<>(listToSort);
        Collections.sort(expected);
        assertEquals(expected, SortingStrategies.mergeSort(listToSort));
    }

    @Test
    void massTestInsertionSort() {
        final List<Integer> list = new ArrayList<>(50000);
        IntStream.range(0, 50000).forEach(n -> list.add(50000 - n));
        List<Integer> sorted = SortingStrategies.insertionSort(list);
        assertEquals(1, sorted.get(0));
        assertEquals(50000, sorted.get(49999));
    }

    @Test
    void massTestMergeSort() {
        final List<Integer> list = new ArrayList<>(50000);
        IntStream.range(0, 50000).forEach(n -> list.add(50000 - n));
        List<Integer> sorted = SortingStrategies.mergeSort(list);
        assertEquals(1, sorted.get(0));
        assertEquals(50000, sorted.get(49999));
    }

    @Test
    void massTestCollectionsSort() {
        final List<Integer> list = new ArrayList<>(50000);
        IntStream.range(0, 50000).forEach(n -> list.add(50000 - n));
        Collections.sort(list);
        assertEquals(1, list.get(0));
        assertEquals(50000, list.get(49999));
    }
}