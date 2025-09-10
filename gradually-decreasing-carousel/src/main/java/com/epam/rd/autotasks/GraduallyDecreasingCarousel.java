package com.epam.rd.autotasks;

public class GraduallyDecreasingCarousel extends DecrementingCarousel {

    public GraduallyDecreasingCarousel(final int capacity) {
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

        // Create a custom CarouselRun that decreases gradually
        return new CarouselRun(elements, size) {
            // Track how many times each element has been decremented
            private final int[] decrementCounts = new int[size];

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

                        // Calculate how much to subtract (1 more than previous times)
                        decrementCounts[currentIndex]++;
                        int decrementAmount = decrementCounts[currentIndex];

                        // Decrease the current element by the calculated amount
                        elements[currentIndex] -= decrementAmount;

                        // If element becomes negative or zero, set it to 0
                        if (elements[currentIndex] <= 0) {
                            elements[currentIndex] = 0;
                        }

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