package com.adventofcode.year2025.day03;

import com.adventofcode.utils.InputReader;

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
            List<Integer> currentLine = List.of(line.trim().split("")).stream().map(Integer::parseInt).toList();

            int largestFirstDigit = 0;
            int largestConcatenatedNumber = 0;
            boolean hasTwoDigits = false;

            for (int digit : currentLine) {
                if (hasTwoDigits){
                     largestConcatenatedNumber = Math.max(largestConcatenatedNumber, largestFirstDigit * 10 + digit);
                }
                largestFirstDigit = Math.max(largestFirstDigit, digit);
                hasTwoDigits = true;
            }
            joltage += largestConcatenatedNumber; 
        }
        return joltage;
    }

    public Long solvePart2(List<String> input) {
        return 0L;
    }
}
