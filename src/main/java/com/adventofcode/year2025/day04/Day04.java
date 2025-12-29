package com.adventofcode.year2025.day04;

import java.util.ArrayList;
import java.util.List;

import com.adventofcode.utils.InputReader;
import com.adventofcode.year2025.day03.Day03;

public class Day04 {
    char paperBale = '@';

    public static void main(String[] args) {
        Day04 day04 = new Day04();
        List<String> input = InputReader.readLines("day04/input.txt");
        System.out.println("Part 1: " + day04.part1(input));
        System.out.println("Part 2: " + day04.part2(input));
    }

    private Long part1(List<String> input) {

        // Initialize variables
        int matrixLength = input.get(0).length(); // assuming all rows have same length
        int matrixHeight = input.size();

        int currentRow = 0;
        int currentCol = 0;
        int maxBaleCount = 4;
        int validBaleCount = 0;

        for (int i = currentRow; i < matrixHeight; i++) {
            for (int j = currentCol; j < matrixLength; j++) {
                if (input.get(i).charAt(j) == paperBale) {
                    int currentBaleCount = 0;
                    
                    // Check all 8 directions with boundary checks
                    // Up
                    if (i > 0 && input.get(i - 1).charAt(j) == paperBale) currentBaleCount++;
                    // Down
                    if (i < matrixHeight - 1 && input.get(i + 1).charAt(j) == paperBale) currentBaleCount++;
                    // Left
                    if (j > 0 && input.get(i).charAt(j - 1) == paperBale) currentBaleCount++;
                    // Right
                    if (j < matrixLength - 1 && input.get(i).charAt(j + 1) == paperBale) currentBaleCount++;
                    // Up-Left
                    if (i > 0 && j > 0 && input.get(i - 1).charAt(j - 1) == paperBale) currentBaleCount++;
                    // Up-Right
                    if (i > 0 && j < matrixLength - 1 && input.get(i - 1).charAt(j + 1) == paperBale) currentBaleCount++;
                    // Down-Left
                    if (i < matrixHeight - 1 && j > 0 && input.get(i + 1).charAt(j - 1) == paperBale) currentBaleCount++;
                    // Down-Right
                    if (i < matrixHeight - 1 && j < matrixLength - 1 && input.get(i + 1).charAt(j + 1) == paperBale) currentBaleCount++;
                    
                    if (currentBaleCount < maxBaleCount) {
                        validBaleCount++;
                    }
                }
            }
        }

        return (long) validBaleCount;
    }

    private Long part2(List<String> input) {
        List<String> currentGrid = new ArrayList<>(input);
        int totalRemoved = 0;
        boolean changed;
        
        do {
            changed = false;
            List<String> nextGrid = new ArrayList<>();
            
            for (int i = 0; i < currentGrid.size(); i++) {
                StringBuilder newRow = new StringBuilder();
                for (int j = 0; j < currentGrid.get(i).length(); j++) {
                    if (currentGrid.get(i).charAt(j) == paperBale) {
                        int currentBaleCount = 0;
                        
                        // Check all 8 directions with boundary checks
                        if (i > 0 && currentGrid.get(i - 1).charAt(j) == paperBale) currentBaleCount++;
                        if (i < currentGrid.size() - 1 && currentGrid.get(i + 1).charAt(j) == paperBale) currentBaleCount++;
                        if (j > 0 && currentGrid.get(i).charAt(j - 1) == paperBale) currentBaleCount++;
                        if (j < currentGrid.get(i).length() - 1 && currentGrid.get(i).charAt(j + 1) == paperBale) currentBaleCount++;
                        if (i > 0 && j > 0 && currentGrid.get(i - 1).charAt(j - 1) == paperBale) currentBaleCount++;
                        if (i > 0 && j < currentGrid.get(i).length() - 1 && currentGrid.get(i - 1).charAt(j + 1) == paperBale) currentBaleCount++;
                        if (i < currentGrid.size() - 1 && j > 0 && currentGrid.get(i + 1).charAt(j - 1) == paperBale) currentBaleCount++;
                        if (i < currentGrid.size() - 1 && j < currentGrid.get(i).length() - 1 && currentGrid.get(i + 1).charAt(j + 1) == paperBale) currentBaleCount++;
                        
                        if (currentBaleCount < 4) {
                            newRow.append('X');
                            totalRemoved++;
                            changed = true;
                        } else {
                            newRow.append(paperBale);
                        }
                    } else {
                        newRow.append(currentGrid.get(i).charAt(j));
                    }
                }
                nextGrid.add(newRow.toString());
            }
            currentGrid = nextGrid;
        } while (changed);
        
        return (long) totalRemoved;
    }

}
