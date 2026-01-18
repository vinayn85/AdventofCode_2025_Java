package com.adventofcode.year2025.day08;

import com.adventofcode.utils.InputReader;
import java.util.*;

public class Day08 {
  public static void main(String[] args) {
    Day08 day = new Day08();
    List<String> lines = InputReader.readLines("day08/input.txt");
    System.out.println("Part 1: " + day.part1(lines));
    System.out.println("Part 2: " + day.part2(lines));
  }

  private Long part1(List<String> lines) {
    // Parse all junction box coordinates
    List<Point> points = new ArrayList<>();
    for (String line : lines) {
      String[] coords = line.split(",");
      points.add(new Point(
          Integer.parseInt(coords[0]),
          Integer.parseInt(coords[1]),
          Integer.parseInt(coords[2])
      ));
    }

    // Calculate all pairwise distances
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        double distance = calculateDistance(points.get(i), points.get(j));
        edges.add(new Edge(i, j, distance));
      }
    }

    // Sort edges by distance (ascending)
    Collections.sort(edges);

    // Use Union-Find to connect the 1000 closest pairs
    UnionFind uf = new UnionFind(points.size());
    int connectionsCount = 0;
    int targetConnections = Math.min(1000, edges.size()); // Can't make more connections than edges

    for (Edge edge : edges) {
      if (connectionsCount >= targetConnections) {
        break;
      }
      if (uf.union(edge.from, edge.to)) {
        connectionsCount++;
      }
    }

    // Count the size of each circuit
    Map<Integer, Integer> circuitSizes = new HashMap<>();
    for (int i = 0; i < points.size(); i++) {
      int root = uf.find(i);
      circuitSizes.put(root, circuitSizes.getOrDefault(root, 0) + 1);
    }

    // Get the three largest circuit sizes
    List<Integer> sizes = new ArrayList<>(circuitSizes.values());
    Collections.sort(sizes, Collections.reverseOrder());

    // Multiply the three largest (if we have at least 3 circuits)
    if (sizes.size() >= 3) {
      long result = (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
      return result;
    } else if (sizes.size() == 2) {
      return (long) sizes.get(0) * sizes.get(1);
    } else if (sizes.size() == 1) {
      return (long) sizes.get(0);
    }
    return 0L;
  }

  private Long part2(List<String> lines) {
    // Parse all junction box coordinates
    List<Point> points = new ArrayList<>();
    for (String line : lines) {
      String[] coords = line.split(",");
      points.add(new Point(
          Integer.parseInt(coords[0]),
          Integer.parseInt(coords[1]),
          Integer.parseInt(coords[2])
      ));
    }

    // Calculate all pairwise distances
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        double distance = calculateDistance(points.get(i), points.get(j));
        edges.add(new Edge(i, j, distance));
      }
    }

    // Sort edges by distance (ascending)
    Collections.sort(edges);

    // Connect pairs one by one until all boxes are in the same circuit
    UnionFind uf = new UnionFind(points.size());
    for (Edge edge : edges) {
      uf.union(edge.from, edge.to);

      // Check if all boxes are now in the same circuit
      if (uf.getComponentCount() == 1) {
        // Return the distance of the edge that connected everything
        return (long) edge.distance;
      }
    }

    return 0L;
  }

  private double calculateDistance(Point p1, Point p2) {
    return Math.sqrt(
        Math.pow(p1.x - p2.x, 2) +
        Math.pow(p1.y - p2.y, 2) +
        Math.pow(p1.z - p2.z, 2)
    );
  }

  // Union-Find (Disjoint Set Union) data structure
  private static class UnionFind {
    private int[] parent;
    private int[] rank;
    private int componentCount;

    public UnionFind(int size) {
      parent = new int[size];
      rank = new int[size];
      componentCount = size;
      for (int i = 0; i < size; i++) {
        parent[i] = i;
        rank[i] = 0;
      }
    }

    public int find(int x) {
      if (parent[x] != x) {
        parent[x] = find(parent[x]); // Path compression
      }
      return parent[x];
    }

    public boolean union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);

      if (rootX == rootY) {
        return false; // Already in the same set
      }

      // Union by rank
      if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
      } else if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
      } else {
        parent[rootY] = rootX;
        rank[rootX]++;
      }

      componentCount--;
      return true;
    }

    public int getComponentCount() {
      return componentCount;
    }
  }

  // Class to represent a 3D point
  private static class Point {
    int x, y, z;

    public Point(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }
  }

  // Class to represent an edge (connection) between two points
  private static class Edge implements Comparable<Edge> {
    int from, to;
    double distance;

    public Edge(int from, int to, double distance) {
      this.from = from;
      this.to = to;
      this.distance = distance;
    }

    @Override
    public int compareTo(Edge other) {
      return Double.compare(this.distance, other.distance);
    }
  }
}
