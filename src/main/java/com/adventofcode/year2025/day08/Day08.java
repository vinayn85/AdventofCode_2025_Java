package com.adventofcode.year2025.day08;

import com.adventofcode.utils.InputReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Day08 {
  public static void main(String[] args) {
    Day08 day = new Day08();
    List<String> lines = InputReader.readLines("day08/input.txt");
    System.out.println("Part 1: " + day.part1(lines));
    // System.out.println("Part 2: "+day.part2(lines));
  }

  private Long part1(List<String> lines) {
    List<Set<String>> circuitList = new ArrayList<>();
    for (String line : lines) {
      String currentJunctionBox = line;
      System.out.println("Current junction box: " + currentJunctionBox);

      for (int i = 0; i < lines.size() - 1; i++) {
        String secondJunctionBox = lines.get(i + 1);
        

        if ( secondJunctionBox.equals(currentJunctionBox)) {
          System.out.println("Skipping");
          continue;
        }

        Double distanceBetweenJunctionBoxes = 0D;
        Double smallestDistance = 0D;
        String junctionBoxWithSmallestDistance="";

        String[] firstJunctionBoxArray = currentJunctionBox.split(",");
        String[] secondJunctionBoxArray = secondJunctionBox.split(",");

        distanceBetweenJunctionBoxes =
            Math.sqrt(
                Math.pow(
                        Double.parseDouble(firstJunctionBoxArray[0])
                            - Double.parseDouble(secondJunctionBoxArray[0]),
                        2)
                    + Math.pow(
                        Double.parseDouble(firstJunctionBoxArray[1])
                            - Double.parseDouble(secondJunctionBoxArray[1]),
                        2)
                    + Math.pow(
                        Double.parseDouble(firstJunctionBoxArray[2])
                            - Double.parseDouble(secondJunctionBoxArray[2]),
                        2));

        System.out.println("Distance between junction boxes: " + distanceBetweenJunctionBoxes);
       if (smallestDistance == 0D ){
        smallestDistance = distanceBetweenJunctionBoxes;
        junctionBoxWithSmallestDistance = currentJunctionBox;
       }else{
        smallestDistance = Math.min(smallestDistance, distanceBetweenJunctionBoxes);
        if (smallestDistance > distanceBetweenJunctionBoxes) {
          junctionBoxWithSmallestDistance = secondJunctionBox;
        }
       }
       System.out.println("Smallest distance: " + smallestDistance);
       System.out.println("Junction box with smallest distance: " + junctionBoxWithSmallestDistance);
      }
      break;
    }

    return 0L;
  }

  private Long part2(List<String> lines) {
    return 0L;
  }
}
