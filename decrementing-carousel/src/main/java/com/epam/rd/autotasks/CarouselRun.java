package com.epam.rd.autotasks;

public class CarouselRun {
    private final int[] elements;
    private final int size;
    private int currentIndex;

    // Package-private constructor to be called by DecrementingCarousel
    CarouselRun(int[] elements, int size) {
        // Create a copy of the elements array to avoid external modification
        this.elements = new int[size];
        System.arraycopy(elements, 0, this.elements, 0, size);
        this.size = size;
        this.currentIndex = 0;
    }

    public int next() {
        // If no elements exist, return -1
        if (size == 0) {
            return -1;
        }

        // Find the next non-zero element starting from current position
        int startIndex = currentIndex;
        do {
            if (elements[currentIndex] > 0) {
                // Get current value
                int currentValue = elements[currentIndex];

                // Decrement the current element
                elements[currentIndex]--;

                // Move to next index for next call
                currentIndex = (currentIndex + 1) % size;

                return currentValue;
            }

            // Move to next index to continue searching
            currentIndex = (currentIndex + 1) % size;

        } while (currentIndex != startIndex);

        // If we've gone through all elements and found no positive values, return -1
        return -1;
    }

    public boolean isFinished() {
        // Check if all elements are zero
        for (int i = 0; i < size; i++) {
            if (elements[i] > 0) {
                return false;
            }
        }
        return true;
    }
}