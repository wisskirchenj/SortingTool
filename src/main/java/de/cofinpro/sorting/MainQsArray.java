package de.cofinpro.sorting;

import java.util.Scanner;

public class MainQsArray {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        scanner.nextLine();
        int[] arr = scanner.tokens().mapToInt(Integer::parseInt).toArray();

        quickSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi || lo < 0) {
            return;
        }
        var p = partition(nums, lo, hi);

        quickSort(nums, lo, p - 1);// Left side of pivot
        quickSort(nums, p + 1, hi); // Right side of pivot
    }

    private static int partition(int[] nums, int lo, int hi) {
        var pivot = nums[hi];

        var i = lo - 1;
        for (var j = lo; j < hi; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, hi);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        array[i] += array[j];
        array[j] = array[i] - array[j];
        array[i] -= array[j];
    }
}
