package com.adventofcode.year2025.day02;

import com.adventofcode.utils.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {
    public static void main(String[] args) {
        Day02 solution = new Day02();

        List<String> input = InputReader.readLines("day02/input.txt");

        System.out.println("Part 1: " + solution.solvePart1(input));
        System.out.println("Part 2: " + solution.solvePart2(input));
    }

    public long solvePart1(List<String> input) {
        List<Long> idSum = new ArrayList<>();
        List<String> inputLines = new ArrayList<>();
        input.stream().map(item -> Arrays.stream(item.split(",")).sequential().toList()).forEach(inputLines::addAll);
        inputLines.forEach(currentRange -> {
            long startPosition = Long.parseLong(currentRange.split("-")[0]);
            long endPosition = Long.parseLong(currentRange.split("-")[1]);
            for (long i = startPosition; i <= endPosition; i++) {
                String idStr = String.valueOf(i);
                int len = idStr.length();
                boolean isRepeated = false;

                if (len % 2 == 0) {
                    String half = idStr.substring(0, len / 2);
                    if (idStr.equals(half + half)) {
                        isRepeated = true;
                    }
                }

                if (!isRepeated && len > 1) {
                    for (int divisor = 1; divisor <= len / 2; divisor++) {
                        if (len % divisor == 0) {
                            String pattern = idStr.substring(0, divisor);
                            StringBuilder repeated = new StringBuilder();
                            for (int j = 0; j < len / divisor; j++) {
                                repeated.append(pattern);
                            }
                            if (idStr.equals(repeated.toString()) && len / divisor == 2) {
                                isRepeated = true;
                                break;
                            }
                        }
                    }
                }
                if (isRepeated) {
                    idSum.add((long) i);
                }
            }
        });
        return idSum.stream().mapToLong(Long::longValue).sum();
    }

    public long solvePart2(List<String> input) {
        long idSum = 0;
        List<String> inputLines = new ArrayList<>();
        input.stream().map(item -> Arrays.stream(item.split(",")).sequential().toList()).forEach(inputLines::addAll);

        for (String currentRange : inputLines) {
            long startPosition = Long.parseLong(currentRange.split("-")[0]);
            long endPosition = Long.parseLong(currentRange.split("-")[1]);
            for (long i = startPosition; i <= endPosition; i++) {
                if (isRepeatedSequence(i)) {
                    idSum += i;
                }
            }
        }
        return idSum;
    }

    private boolean isRepeatedSequence(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();
        for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
            if (len % patternLen != 0) {
                continue;
            }
            int repeats = len / patternLen;
            if (repeats < 2) {
                continue;
            }
            String pattern = idStr.substring(0, patternLen);
            StringBuilder repeated = new StringBuilder(len);
            for (int j = 0; j < repeats; j++) {
                repeated.append(pattern);
            }
            if (idStr.contentEquals(repeated)) {
                return true;
            }
        }
        return false;
    }
}
