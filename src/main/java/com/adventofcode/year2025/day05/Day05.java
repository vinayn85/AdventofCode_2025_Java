package com.adventofcode.year2025.day05;

import com.adventofcode.utils.InputReader;
import java.util.ArrayList;
import java.util.List;

public class Day05 {
  public static void main(String[] args) {
    Day05 day05 = new Day05();
    System.out.println("Part 1: " + day05.part1());
    System.out.println("Part 2: " + day05.part2());
  }

  public Long part1() {
    Long freshIdCount = 0L;

    List<String> ranges = getIngredientIDRange();
    List<String> ids = getIngredientIdsToCheck();

    for (String id : ids) {
      boolean isRepeated = false;
      Long idValue = Long.parseLong(id.trim());
      for (String range : ranges) {
        String[] parts = range.split("-");
        Long startRange = Long.parseLong(parts[0].trim());
        Long endRange = Long.parseLong(parts[1].trim());

        // Check if ID is within range using comparison instead of iteration. Tricky bit. Large
        // number ranges will kill the program.
        // So very easy to miss this
        if (idValue >= startRange && idValue <= endRange) {
          if (!isRepeated) {
            freshIdCount++;
          }
          isRepeated = true;
        }
      }
    }
    return freshIdCount;
  }

  public Long part2() {
    List<String> ranges = getIngredientIDRange();
    List<long[]> rangePairs = new ArrayList<>();

    // Parse all ranges
    for (String range : ranges) {
      String[] parts = range.split("-");
      long start = Long.parseLong(parts[0].trim());
      long end = Long.parseLong(parts[1].trim());
      rangePairs.add(new long[] {start, end});
    }

    // Sort ranges by start value
    rangePairs.sort((a, b) -> Long.compare(a[0], b[0]));

    // Merge overlapping ranges
    List<long[]> mergedRanges = new ArrayList<>();
    for (long[] current : rangePairs) {
      if (mergedRanges.isEmpty()) {
        mergedRanges.add(current);
      } else {
        long[] last = mergedRanges.get(mergedRanges.size() - 1);
        if (current[0] <= last[1] + 1) {
          // Overlapping or adjacent, merge them
          last[1] = Math.max(last[1], current[1]);
        } else {
          // No overlap, add new range
          mergedRanges.add(current);
        }
      }
    }

    // Calculate total unique IDs covered
    long totalIds = 0;
    for (long[] range : mergedRanges) {
      totalIds += (range[1] - range[0] + 1);
    }

    return totalIds;
  }

  private List<String> getIngredientIDRange() {
    List<String> lines = InputReader.readLines("day05/input.txt");
    List<String> ranges = new ArrayList<>();
    lines.stream()
        .forEach(
            line -> {
              String trimmedLine = line.trim();
              if (!trimmedLine.isEmpty() && trimmedLine.contains("-")) {
                ranges.add(trimmedLine);
              }
            });
    return ranges;
  }

  private List<String> getIngredientIdsToCheck() {

    List<String> lines = InputReader.readLines("day05/input.txt");
    List<String> ids = new ArrayList<>();
    lines.stream()
        .forEach(
            line -> {
              String trimmedLine = line.trim();
              if (!trimmedLine.isEmpty() && !trimmedLine.contains("-")) {
                ids.add(trimmedLine);
              }
            });
    return ids;
  }
}
