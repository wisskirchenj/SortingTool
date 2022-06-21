package de.cofinpro.sorting;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Slf4j
public class DutchFlag {


    public static void main(String[] args) throws IOException {
        final var text = Files.readString(Path.of("src/main/resources/hyperskill-dataset-87688273.txt"));
        final var nums = Arrays.stream(text.split("\\s")).mapToInt(Integer::parseInt).toArray();
        var result = new int[nums.length];
        Arrays.fill(result, 1);
        int zeroIndex = 0;
        int twoIndex = nums.length - 1;
        for (int n : nums) {
            if (n == 0) {
                result[zeroIndex++] = 0;
            }
            if (n == 2) {
                result[twoIndex--] = 2;
            }
        }
        log.info(String.join(" ",
                Arrays.stream(result).mapToObj(String::valueOf).toArray(String[]::new)));
    }
}
