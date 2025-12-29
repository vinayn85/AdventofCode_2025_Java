package com.adventofcode.year2025.day05;

import java.util.ArrayList;
import java.util.List;

import com.adventofcode.utils.InputReader;

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
                    
                    // Check if ID is within range using comparison instead of iteration. Tricky bit. Large number ranges will kill the program.
                    // So very eassy to miss this
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
        return 0L;
    }

    private List<String> getIngredientIDRange() {
        List<String> lines = InputReader.readLines("day05/input.txt");
        List<String> ranges = new ArrayList<>();
        lines.stream().forEach(line -> {
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
        lines.stream().forEach(line -> {
            String trimmedLine = line.trim();
            if (!trimmedLine.isEmpty() && !trimmedLine.contains("-")) {
                ids.add(trimmedLine);
            }
        });
        return ids;
    }
}
