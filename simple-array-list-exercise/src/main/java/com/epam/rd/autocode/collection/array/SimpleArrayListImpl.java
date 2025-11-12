package com.epam.rd.autocode.collection.array;

import java.util.*;

public class SimpleArrayListImpl implements SimpleArrayList {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int FACTOR_MULTIPLIER = 2;
    private static final double INCREASE_LOAD_FACTOR = 0.75;
    private static final double DECREASE_LOAD_FACTOR = 0.4;

    private Object[] elements;
    private int size;

    /**
     * Creates a list with the default capacity = 10.
     */
    public SimpleArrayListImpl() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(Object element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        // Check if adding this element would exceed 75% capacity
        if (size + 1 > elements.length * INCREASE_LOAD_FACTOR) {
            increaseCapacity();
        }

        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    private void increaseCapacity() {
        // Double current capacity and multiply by load factor (0.75)
        int newCapacity = (int) (elements.length * FACTOR_MULTIPLIER * INCREASE_LOAD_FACTOR);
        // Ensure we don't go below minimum capacity
        if (newCapacity < DEFAULT_CAPACITY) {
            newCapacity = DEFAULT_CAPACITY;
        }

        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public boolean decreaseCapacity() {
        // Don't decrease if we're at or below default capacity
        if (elements.length <= DEFAULT_CAPACITY) {
            return false;
        }

        double currentLoadFactor = (double) size / elements.length;

        // Only decrease if 40% full or less (<= 0.4)
        if (currentLoadFactor > DECREASE_LOAD_FACTOR) {
            return false;
        }

        // Calculate new capacity by doubling current number of elements
        int newCapacity = size * FACTOR_MULTIPLIER;

        // Ensure we don't go below default capacity
        if (newCapacity < DEFAULT_CAPACITY) {
            newCapacity = DEFAULT_CAPACITY;
        }

        // Don't decrease if new capacity isn't smaller than current capacity
        if (newCapacity >= elements.length) {
            return false;
        }

        // Create new smaller array
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        return true;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    @Override
    public Optional<Object> remove(Object el) {
        if (el == null) {
            throw new NullPointerException("Element cannot be null");
        }

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(el)) {
                Object removedElement = elements[i];

                // Shift elements left
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }

                // Clear last element and decrease size
                elements[size - 1] = null;
                size--;

                return Optional.of(removedElement);
            }
        }
        return Optional.empty();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(elements[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}