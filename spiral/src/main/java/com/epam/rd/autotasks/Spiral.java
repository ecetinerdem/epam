package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        // Create the result matrix
        int[][] result = new int[rows][columns];

        // Handle edge cases
        if (rows == 0 || columns == 0) {
            return result;
        }

        // Define boundaries for our spiral
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = columns - 1;

        int num = 1; // Start filling from 1

        // Continue until we've filled all positions
        while (top <= bottom && left <= right) {

            // 1. Fill top row from left to right
            for (int j = left; j <= right; j++) {
                result[top][j] = num++;
            }
            top++; // Move top boundary down

            // 2. Fill right column from top to bottom
            for (int i = top; i <= bottom; i++) {
                result[i][right] = num++;
            }
            right--; // Move right boundary left

            // 3. Fill bottom row from right to left (if there's still a row to fill)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result[bottom][j] = num++;
                }
                bottom--; // Move bottom boundary up
            }

            // 4. Fill left column from bottom to top (if there's still a column to fill)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[i][left] = num++;
                }
                left++; // Move left boundary right
            }
        }

        return result;
    }
}
