package com.adventofcode.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CircularList<T> implements Iterable<T> {
  private final List<T> data;

  @SafeVarargs
  public CircularList(T... elements) {
    this.data = new ArrayList<>(List.of(elements));
  }

  public CircularList(List<T> elements) {
    this.data = new ArrayList<>(elements);
  }

  public CircularList(int capacity) {
    this.data = new ArrayList<>(capacity);
  }

  public T get(int index) {
    if (data.isEmpty()) {
      throw new IllegalStateException("Cannot get from empty circular list");
    }
    int normalizedIndex = ((index % data.size()) + data.size()) % data.size();
    return data.get(normalizedIndex);
  }

  public void set(int index, T value) {
    if (data.isEmpty()) {
      throw new IllegalStateException("Cannot set in empty circular list");
    }
    int normalizedIndex = ((index % data.size()) + data.size()) % data.size();
    data.set(normalizedIndex, value);
  }

  public void add(T element) {
    data.add(element);
  }

  public int size() {
    return data.size();
  }

  public int currentSize() {
    return data.size();
  }

  public List<T> toList() {
    return new ArrayList<>(data);
  }

  public T getNext(int currentIndex) {
    return get(currentIndex + 1);
  }

  public T getPrevious(int currentIndex) {
    return get(currentIndex - 1);
  }

  public void rotate(int positions) {
    if (data.isEmpty()) return;

    int normalizedPositions = ((positions % data.size()) + data.size()) % data.size();
    if (normalizedPositions == 0) return;

    List<T> rotated = new ArrayList<>(data.size());
    for (int i = 0; i < data.size(); i++) {
      rotated.add(get(i - normalizedPositions));
    }
    data.clear();
    data.addAll(rotated);
  }

  @Override
  public Iterator<T> iterator() {
    return data.iterator();
  }

  @Override
  public String toString() {
    return data.toString();
  }
}
