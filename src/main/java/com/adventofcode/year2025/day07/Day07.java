package com.adventofcode.year2025.day07;

import com.adventofcode.utils.InputReader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day07 {

    public static void main(String[] args) {
        Day07 day = new Day07();
        List<String> lines = InputReader.readLines("day07/input.txt");
        System.out.println("Part 1: " + day.part1(lines));
        System.out.println("Part 2: " + day.part2(lines));
    }

    public long part1(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return 0;
        }

        char[][] grid = parseGrid(lines);
        int rows = grid.length;
        int cols = grid[0].length;

        // Find starting position 'S' in first row
        int startCol = -1;
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] == 'S') {
                startCol = col;
                break;
            }
        }

        if (startCol == -1) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        long count = countSplits(grid, 0, startCol, visited);
        return count;
    }

    public long part2(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return 0;
        }

        char[][] grid = parseGrid(lines);
        int rows = grid.length;
        int cols = grid[0].length;

        // Find starting position 'S' in first row
        int startCol = -1;
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] == 'S') {
                startCol = col;
                break;
            }
        }

        if (startCol == -1) {
            return 0;
        }

        // Use memoization to cache the number of timelines from each position
        Map<String, Long> memo = new HashMap<>();
        return countTimelines(grid, 0, startCol, memo);
    }

    private char[][] parseGrid(List<String> lines) {
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        return grid;
    }

    private long countSplits(char[][] grid, int row, int col, Set<String> visited) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check bounds
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return 0;
        }

        // Check if already visited
        String position = row + "," + col;
        if (visited.contains(position)) {
            return 0;
        }

        visited.add(position);

        char cell = grid[row][col];

        if (cell == '^') {
            // Hit a splitter - count this split and branch in two directions
            long leftCount = countSplits(grid, row + 1, col - 1, visited);
            long rightCount = countSplits(grid, row + 1, col + 1, visited);
            return 1 + leftCount + rightCount;
        } else if (cell == '.' || cell == 'S') {
            // Continue straight down
            return countSplits(grid, row + 1, col, visited);
        }

        return 0;
    }

    private long countTimelines(char[][] grid, int row, int col, Map<String, Long> memo) {
        int rows = grid.length;
        int cols = grid[0].length;

        // If we go off the grid, this completes one timeline
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return 1;
        }

        // Check memoization cache
        String key = row + "," + col;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char cell = grid[row][col];
        long result;

        if (cell == '^') {
            // Hit a splitter - branch into two timelines
            long leftCount = countTimelines(grid, row + 1, col - 1, memo);
            long rightCount = countTimelines(grid, row + 1, col + 1, memo);
            result = leftCount + rightCount;
        } else if (cell == '.' || cell == 'S') {
            // Continue straight down
            result = countTimelines(grid, row + 1, col, memo);
        } else {
            // Invalid cell
            result = 0;
        }

        memo.put(key, result);
        return result;
    }
}
