package com.adventofcode.year2025.day03;

import com.adventofcode.utils.InputReader;
import java.util.ArrayList;
import java.util.List;

public class Day03 {

  public static void main(String[] args) {
    Day03 solution = new Day03();
    List<String> input = InputReader.readLines("day03/input.txt");

    System.out.println("Part 1: " + solution.solvePart1(input));
    System.out.println("Part 2: " + solution.solvePart2(input));
  }

  public Long solvePart1(List<String> input) {
    Long joltage = 0L;
    for (String line : input) {
      List<Integer> currentLine =
          List.of(line.trim().split("")).stream().map(Integer::parseInt).toList();

      int largestFirstDigit = 0;
      int largestConcatenatedNumber = 0;
      boolean hasTwoDigits = false;

      for (int digit : currentLine) {
        if (hasTwoDigits) {
          largestConcatenatedNumber =
              Math.max(largestConcatenatedNumber, largestFirstDigit * 10 + digit);
        }
        largestFirstDigit = Math.max(largestFirstDigit, digit);
        hasTwoDigits = true;
      }
      joltage += largestConcatenatedNumber;
    }
    return joltage;
  }

  public Long solvePart2(List<String> input) {
    Long joltage = 0L;
    for (String line : input) {
      List<Integer> currentLine =
          List.of(line.trim().split("")).stream().map(Integer::parseInt).toList();

      int n = currentLine.size();
      int k = 12; // Number of digits to select
      List<Integer> result = new ArrayList<>();

      int startIdx = 0;
      for (int pos = 0; pos < k; pos++) {
        int endIdx = n - (k - pos);

        int maxDigit =
            currentLine.subList(startIdx, endIdx + 1).stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        for (int i = startIdx; i <= endIdx; i++) {
          if (currentLine.get(i) == maxDigit) {
            startIdx = i + 1; // Next search starts after this digit
            break;
          }
        }

        result.add(maxDigit);
      }

      // Convert result to long
      long number = 0;
      for (int digit : result) {
        number = number * 10 + digit;
      }
      joltage += number;
    }
    return joltage;
  }
}
