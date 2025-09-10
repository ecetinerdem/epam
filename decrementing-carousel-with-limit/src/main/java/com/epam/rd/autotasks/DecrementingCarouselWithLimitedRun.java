package com.epam.rd.autotasks;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel {
    private final int actionLimit;

    public DecrementingCarouselWithLimitedRun(final int capacity, final int actionLimit) {
        super(capacity);
        this.actionLimit = actionLimit;
    }

    @Override
    public CarouselRun run() {
        // If run() has already been called, return null
        if (hasRun) {
            return null;
        }

        // Mark as having run
        hasRun = true;

        // Create a custom CarouselRun that limits the number of next() calls
        return new CarouselRun(elements, size) {
            private int callCount = 0; // Track how many times next() has been called

            @Override
            public int next() {
                // Check if we've reached the limit
                if (callCount >= actionLimit) {
                    return -1;
                }

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

                        // Decrement the current element (normal behavior)
                        elements[currentIndex]--;

                        // Move to next index for next call
                        currentIndex = (currentIndex + 1) % size;

                        // Increment the call counter
                        callCount++;

                        return currentValue;
                    }

                    // Move to next index to continue searching
                    currentIndex = (currentIndex + 1) % size;

                } while (currentIndex != startIndex);

                // If we've gone through all elements and found no positive values, return -1
                return -1;
            }

            @Override
            public boolean isFinished() {
                // Finished if we've reached the call limit OR all elements are zero
                if (callCount >= actionLimit) {
                    return true;
                }

                // Check if all elements are zero (original logic)
                for (int i = 0; i < size; i++) {
                    if (elements[i] > 0) {
                        return false;
                    }
                }
                return true;
            }
        };
    }
}