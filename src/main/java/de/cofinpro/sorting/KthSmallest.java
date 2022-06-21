package de.cofinpro.sorting;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Slf4j
public class KthSmallest {

    public static void main(String[] args) throws IOException {
        final var lines = Files.readString(Path.of("src/main/resources/hyperskill-dataset-87683366.txt")).split("\n");
        final var elements = Arrays.asList(lines[0].split(" "));
        var k = Long.parseLong(lines[1]);
        log.info("{}-th smallest element: {}", k, elements.stream()
                .mapToInt(Integer::parseInt)
                //.distinct()
                .sorted()
                .skip(k - 1)
                .findFirst().orElse(-1));
    }
}