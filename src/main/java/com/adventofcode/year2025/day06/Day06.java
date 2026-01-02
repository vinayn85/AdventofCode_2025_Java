package com.adventofcode.year2025.day06;

import com.adventofcode.utils.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {

    public static void main(String[] args) {
        Day06 day = new Day06();
        List<String> lines = InputReader.readLines("day06/input.txt");
        System.out.println("Part 1: " + day.part1(lines));
        System.out.println("Part 2: " + day.part2(lines));
    }

    public Long part1(List<String> lines) {
        Long grandTotal = 0L;
        List<List<String>> rearrangedLines = new ArrayList<>();
        List<List<String>> cleanedLines = new ArrayList<>();
        lines.forEach(line -> {
            List<String> cleanedLine = Arrays.stream(line.trim().replaceAll("\s+", ",").split(",")).toList();
            cleanedLines.add(cleanedLine);
        });

        Long columns = Long.valueOf(cleanedLines.get(0).size());
        Long rows = Long.valueOf(cleanedLines.size());

        for (Long i = 0L; i < columns; i++) {
            List<String> rearrangedLine = new ArrayList<>();
            for (Long j = 0L; j < rows; j++) {
                rearrangedLine.add(cleanedLines.get(j.intValue()).get(i.intValue()));
            }
            rearrangedLines.add(rearrangedLine);
        }
        for (List<String> line : rearrangedLines) {
            if (line.contains("+")) {
                Long sum = line.stream()
                        .filter(item -> !item.equals("*") && !item.equals("+"))
                        .mapToLong(Long::parseLong)
                        .sum();
                grandTotal += sum;
            } else if (line.contains("*")) {
                Long product = line.stream()
                        .filter(item -> !item.equals("*") && !item.equals("+"))
                        .mapToLong(Long::parseLong)
                        .reduce(1, (a, b) -> a * b);
                grandTotal += product;
            }
        }
        return grandTotal;
    }

    public Long part2(List<String> lines) {
        Long grandTotal = 0L;

        if (lines.isEmpty()) return 0L;

        int numRows = lines.size();

        int maxColumns = 0;
        for (String line : lines) {
            maxColumns = Math.max(maxColumns, line.length());
        }

        StringBuilder currentExpression = new StringBuilder();

        for (int col = maxColumns - 1; col >= 0; col--) {
            StringBuilder columnChars = new StringBuilder();
            for (String line : lines) {
                if (col < line.length()) {
                    columnChars.append(line.charAt(col));
                } else {
                    columnChars.append(' ');
                }
            }

            String verticalStr = columnChars.toString();
            currentExpression.insert(0, verticalStr);

            if (verticalStr.trim().isEmpty() && !currentExpression.toString().trim().isEmpty()) {
                String expr = currentExpression.toString();
                int numCols = expr.length() / numRows;

                List<Long> numbers = new ArrayList<>();
                List<Character> operators = new ArrayList<>();

                for (int c = 0; c < numCols; c++) {
                    StringBuilder columnNumber = new StringBuilder();
                    Character columnOperator = null;

                    for (int row = 0; row < numRows; row++) {
                        int index = c * numRows + row;
                        if (index < expr.length()) {
                            char ch = expr.charAt(index);
                            if (row < numRows - 1) {
                                if (Character.isDigit(ch)) {
                                    columnNumber.append(ch);
                                }
                            } else {
                                if (ch == '+' || ch == '*') {
                                    columnOperator = ch;
                                }
                            }
                        }
                    }

                    if (columnNumber.length() > 0) {
                        numbers.add(Long.parseLong(columnNumber.toString()));
                    }
                    if (columnOperator != null) {
                        operators.add(columnOperator);
                    }
                }

                java.util.Collections.reverse(numbers);

                Long result = evaluateProblem(numbers, operators);
                grandTotal += result;
                currentExpression = new StringBuilder();
            }
        }

        if (!currentExpression.toString().trim().isEmpty()) {
            String expr = currentExpression.toString();
            int numCols = expr.length() / numRows;

            List<Long> numbers = new ArrayList<>();
            List<Character> operators = new ArrayList<>();

            for (int c = 0; c < numCols; c++) {
                StringBuilder columnNumber = new StringBuilder();
                Character columnOperator = null;

                for (int row = 0; row < numRows; row++) {
                    int index = c * numRows + row;
                    if (index < expr.length()) {
                        char ch = expr.charAt(index);
                        if (row < numRows - 1) {
                            if (Character.isDigit(ch)) {
                                columnNumber.append(ch);
                            }
                        } else {
                            if (ch == '+' || ch == '*') {
                                columnOperator = ch;
                            }
                        }
                    }
                }

                if (columnNumber.length() > 0) {
                    numbers.add(Long.parseLong(columnNumber.toString()));
                }
                if (columnOperator != null) {
                    operators.add(columnOperator);
                }
            }

            java.util.Collections.reverse(numbers);

            Long result = evaluateProblem(numbers, operators);
            grandTotal += result;
        }
        return grandTotal;
    }

    private Long evaluateProblem(List<Long> numbers, List<Character> operators) {
        if (numbers.isEmpty()) return 0L;
        if (numbers.size() == 1) return numbers.get(0);
        if (operators.isEmpty()) return numbers.get(0);

        // Use the first operator for all operations
        char operator = operators.get(0);

        Long result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (operator == '*') {
                result = result * numbers.get(i);
            } else if (operator == '+') {
                result = result + numbers.get(i);
            }
        }

        return result;
    }
}
