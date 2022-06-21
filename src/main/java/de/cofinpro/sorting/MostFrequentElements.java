package de.cofinpro.sorting;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class MostFrequentElements {

    public static void main(String[] args) throws IOException {
        final var lines = Files.readString(Path.of("src/main/resources/hyperskill-dataset-87726943.txt"))
                .split("\n");
        final var elements = Arrays.asList(lines[0].split(" "));
        var k = Long.parseLong(lines[1]);
        var result = elements.stream().collect(Collectors.groupingBy(Integer::parseInt, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .map(e -> e.getKey().toString())
                .collect(Collectors.joining(" "));
        log.info(result);
    }
}