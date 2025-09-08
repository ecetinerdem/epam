package com.epam.rd.autotasks;

public class DecrementingCarousel {
    private final int capacity;
    private final int[] elements;
    private int size;
    private boolean hasRun;

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
        this.size = 0;
        this.hasRun = false;
    }

    public boolean addElement(int element) {
        // Reject if element is non-positive
        if (element <= 0) {
            return false;
        }

        // Reject if carousel is full
        if (size >= capacity) {
            return false;
        }

        // Reject if run() has already been called
        if (hasRun) {
            return false;
        }

        // Add the element
        elements[size] = element;
        size++;
        return true;
    }

    public CarouselRun run() {
        // If run() has already been called, return null
        if (hasRun) {
            return null;
        }

        // Mark as having run
        hasRun = true;

        // Create and return CarouselRun
        return new CarouselRun(elements, size);
    }
}