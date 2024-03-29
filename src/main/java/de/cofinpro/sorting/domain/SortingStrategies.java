package de.cofinpro.sorting.domain;

import java.util.*;

/**
 * class with static methods offering generic implementations of
 * 1. the insertion sort algorithm
 * 2. the merge sort algorithm
 * The generic type parameter T must implement Comparable<T>.
 */
public class SortingStrategies {

    private static final Random rand = new Random();
    private SortingStrategies() {
        // no instances
    }

    /**
     * implementation of the simple - but for large unordered data too slow - insertion sort algorithm
     *
     * @param list the unsorted list
     * @param <T>  the entry type of the given list implementing Comparable<T>
     * @return the sorted list
     */
    public static <T extends Comparable<T>> List<T> insertionSort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && list.get(j).compareTo(list.get(j - 1)) < 0) {
                Collections.swap(list, j, --j);
            }
        }
        return list;
    }

    public static <T extends Comparable<T>> List<T> quickSort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int lo, int hi) {
        // Ensure indices are in correct order
        if (lo >= hi || lo < 0) {
            return;
        }

        // Choose a random pivot and move it to the end
        int pivotIndex = lo + rand.nextInt(hi - lo + 1);
        Collections.swap(list, pivotIndex, hi);

        // Partition array and get the pivot index
        var p = partition(list, lo, hi);

        // Sort the two partitions
        quickSort(list, lo, p - 1);// Left side of pivot
        quickSort(list, p + 1, hi); // Right side of pivot
    }

    // Divides array into two partitions
    private static  <T extends Comparable<T>> int partition(List<T> list, int lo, int hi) {
        // Choose the last element as the pivot
        var pivot = list.get(hi);

        // Temporary pivot index
        var i = lo - 1;

        for (var j = lo; j < hi; j++) {
            // If the current element is less than or equal to the pivot
            if (list.get(j).compareTo(pivot) <= 0) {
                // Move the temporary pivot index forward
                i++;
                // Swap the current element with the element at the temporary pivot index
                Collections.swap(list, i, j);
            }
        }

        // Move the pivot element to the correct pivot position (between the smaller and larger elements)
        Collections.swap(list, i + 1, hi);
        return i + 1;
    }

    /**
     * implementation of the relatively quick O(n*log(n)) merge sort algorithm. The implementation is bottom-up,
     * i.e. non-recursive, and makes essential use of the Queue<T> interface to merge bottom-up sub lists.
     *
     * @param list the unsorted list
     * @param <T>  the entry type of the given list implementing Comparable<T>
     * @return the sorted list
     */
    public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        if (list.size() < 2) {
            return list;
        }
        List<Queue<T>> bottomUpQueues = list.stream().map(t -> {
            Queue<T> q = new ArrayDeque<>(1);
            q.add(t);
            return q;
        }).toList();
        while (bottomUpQueues.size() > 1) {
            bottomUpQueues = mergeAdjacent(bottomUpQueues);
        }
        return new ArrayList<>(bottomUpQueues.get(0));
    }

    private static <T extends Comparable<T>> List<Queue<T>> mergeAdjacent(List<Queue<T>> queues) {
        List<Queue<T>> mergedQueues = new ArrayList<>(queues.size() / 2 + 1);
        for (int i = 0; i < queues.size() / 2; i++) {
            mergedQueues.add(merge(queues.get(2 * i), queues.get(2 * i + 1)));
        }
        if (queues.size() % 2 == 1) {
            mergedQueues.add(queues.get(queues.size() - 1));
        }
        return mergedQueues;
    }

    private static <T extends Comparable<T>> Queue<T> merge(Queue<T> queue1, Queue<T> queue2) {
        Queue<T> merged = new ArrayDeque<>(queue1.size() + queue2.size());
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            merged.offer(queue1.peek().compareTo(queue2.peek()) < 0 ? queue1.poll() : queue2.poll());
        }
        merged.addAll(queue1.isEmpty() ? queue2 : queue1);
        return merged;
    }
}