package com.adventofcode.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A circular data structure that wraps around when accessing elements beyond its bounds.
 * Useful for problems involving circular buffers or ring structures.
 *
 * @param <T> the type of elements in this list
 */
public class CircularList<T> implements Iterable<T> {
    private final List<T> data;

    /**
     * Creates a circular list with the given elements.
     *
     * @param elements the elements to store in the circular list
     */
    @SafeVarargs
    public CircularList(T... elements) {
        this.data = new ArrayList<>(List.of(elements));
    }

    /**
     * Creates a circular list with the given list of elements.
     *
     * @param elements the list of elements to store
     */
    public CircularList(List<T> elements) {
        this.data = new ArrayList<>(elements);
    }

    /**
     * Creates an empty circular list with the specified capacity.
     *
     * @param capacity the initial capacity
     */
    public CircularList(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    /**
     * Gets the element at the specified index, wrapping around if necessary.
     * Supports negative indices (wraps backwards).
     *
     * @param index the index (can be any integer)
     * @return the element at the circular index
     */
    public T get(int index) {
        if (data.isEmpty()) {
            throw new IllegalStateException("Cannot get from empty circular list");
        }
        int normalizedIndex = ((index % data.size()) + data.size()) % data.size();
        return data.get(normalizedIndex);
    }

    /**
     * Sets the element at the specified index, wrapping around if necessary.
     *
     * @param index the index (can be any integer)
     * @param value the value to set
     */
    public void set(int index, T value) {
        if (data.isEmpty()) {
            throw new IllegalStateException("Cannot set in empty circular list");
        }
        int normalizedIndex = ((index % data.size()) + data.size()) % data.size();
        data.set(normalizedIndex, value);
    }

    /**
     * Adds an element to the circular list.
     *
     * @param element the element to add
     */
    public void add(T element) {
        data.add(element);
    }

    /**
     * Returns the size of the circular list.
     *
     * @return the number of elements
     */
    public int size() {
        return data.size();
    }

    /**
     * Returns the current number of elements in the list.
     *
     * @return the current number of elements
     */
    public int currentSize() {
        return data.size();
    }

    /**
     * Returns the underlying list (not circular).
     *
     * @return the underlying list
     */
    public List<T> toList() {
        return new ArrayList<>(data);
    }

    /**
     * Gets the next element after the given index.
     *
     * @param currentIndex the current index
     * @return the next element (wraps around)
     */
    public T getNext(int currentIndex) {
        return get(currentIndex + 1);
    }

    /**
     * Gets the previous element before the given index.
     *
     * @param currentIndex the current index
     * @return the previous element (wraps around)
     */
    public T getPrevious(int currentIndex) {
        return get(currentIndex - 1);
    }

    /**
     * Rotates the list by the specified number of positions.
     * Positive values rotate right, negative values rotate left.
     *
     * @param positions the number of positions to rotate
     */
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

