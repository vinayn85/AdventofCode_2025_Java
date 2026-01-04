package com.adventofcode.year2025.day07;

import com.adventofcode.utils.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Day07 {
    public static void main(String[] args) {
        Day07 day = new Day07();
        List<String> lines = InputReader.readLines("day07/input.txt");
        System.out.println("Part 1: "+day.part1(lines));
        System.out.println("Part 2: "+day.part2(lines));
    }

    public Long part1(List<String> lines){
        char splitterSymbol = '^';
        char beamStartPointSymbol = 'S';
        List<List<Integer>> splitterPosition = new ArrayList<>();
        for (String line : lines) {
            line.chars().forEach(i->splitterPosition.add(List.of(i)));
        }
        System.out.println(splitterPosition);
        return 0L;
    }

    public Long part2(List<String> lines){
        return 0L;
    }
}

