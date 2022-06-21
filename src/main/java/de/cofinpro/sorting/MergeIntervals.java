package de.cofinpro.sorting;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class MergeIntervals {

    public static void main(String[] args) throws IOException {
        final var input = Files.readString(Path.of("src/main/resources/hyperskill-dataset-87724722.txt"));
        var intervals = parseIntervals(input);
        intervals.sort(Comparator.comparingInt(Interval::start));
        var result = merge(intervals);
        log.info("[{}]", String.join(", ", result));
    }

    private static List<String> merge(List<Interval> intervals) {
        List<String> result = new ArrayList<>();
        var current = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            var next = intervals.get(i);
            if (current.overlaps(next)) {
                current = new Interval(current.start(), Math.max(current.end(), next.end()));
            } else {
                result.add(current.toString());
                current = next;
            }
        }
        result.add(current.toString());
        return result;
    }

    private static List<Interval> parseIntervals(String input) {
        var matcher = Pattern.compile("\\[(\\d+), (\\d+)]").matcher(input);
        return matcher.results()
                .map(r -> new Interval(Integer.parseInt(r.group(1)), Integer.parseInt(r.group(2))))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    record Interval(int start, int end) {
        public boolean overlaps(Interval other) {
            return other.start <= end;
        }

        public String toString() {
            return String.format("[%d, %d]", start, end);
        }
    }
}