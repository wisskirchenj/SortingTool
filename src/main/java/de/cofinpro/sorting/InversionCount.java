package de.cofinpro.sorting;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
public class InversionCount {

    private static long count = 0;

    public static void main(String[] args) throws IOException {
        final var text = Files.readString(Path.of("src/main/resources/hyperskill-dataset-87687641.txt"));
        final var nums = Arrays.stream(text.split("\\s")).mapToInt(Integer::parseInt).toArray();
        IntStream.range(1, nums.length).forEach(i -> reposition(nums, i));
        log.info("Inversions: {}", count);
    }

    private static void reposition(int[] nums, int i) {
        if (nums[i] >= nums[i - 1]) {
            return;
        }
        final var index = getIndex(nums, i);
        var tmp = nums[i];
        count += i - index;
        System.arraycopy(nums, index, nums, index + 1, i - index);
        nums[index] = tmp;
    }

    private static int getIndex(int[] nums, int i) {
        var index = Arrays.binarySearch(nums, 0, i - 1, nums[i]);
        if (index < 0) {
            return -index - 1;
        }
        while (index < i && nums[index] == nums[i]) {
            index++;
        }
        return index;
    }
}
