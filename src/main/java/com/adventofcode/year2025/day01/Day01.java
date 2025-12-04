package com.adventofcode.year2025.day01;

import com.adventofcode.utils.CircularList;
import com.adventofcode.utils.InputReader;

import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        Day01 solution = new Day01();

        // Read input from resources
        List<String> input = InputReader.readLines("day01/input.txt");

        System.out.println("Part 1: " + solution.solvePart1(input));
//        System.out.println("Part 2: " + solution.solvePart2(input));
    }

    public long solvePart1(List<String> input) {
        CircularList<Integer> circularList = new CircularList<>(100);
        final int starting_position = 50;
        int zeroCount = 0;
        // Initialize circular list with positions 0-98
        for (int i = 0; i < 100; i++) {
            circularList.add(i);
        }

        int currentPosition = starting_position;

        for (String line : input) {
            char direction = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (direction == 'R') {
                currentPosition += value;
            } else {
                currentPosition -= value;
            }
            if (circularList.get(currentPosition) == 0) {
                zeroCount++;
            }
        }

        return zeroCount;
    }

    public long solvePart2(List<String> input) {

        return 0;
    }
}

