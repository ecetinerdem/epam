package com.epam.rd.autotasks;

public class HalvingCarousel extends DecrementingCarousel {

    public HalvingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    public CarouselRun run() {
        // If run() has already been called, return null
        if (hasRun) {
            return null;
        }

        // Mark as having run
        hasRun = true;

        // Create a custom CarouselRun that halves instead of decrements
        return new CarouselRun(elements, size) {
            @Override
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

                        // HALVE the current element instead of decrementing
                        elements[currentIndex] = elements[currentIndex] / 2;

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
        };
    }
}