package com.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {
  private InputReader() {}

  public static List<String> readLines(String fileName) {
    try {
      Path path = Path.of("src/main/resources", fileName);
      return Files.readAllLines(path);
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found: " + fileName, e);
    }
  }
}
