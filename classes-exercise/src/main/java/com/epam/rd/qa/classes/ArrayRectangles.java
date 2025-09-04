package com.epam.rd.qa.classes;

public class ArrayRectangles {

    private Rectangle[] rectangleArray;

    // Constructor: create null-elements array of given length
    public ArrayRectangles(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        rectangleArray = new Rectangle[size]; // initialize array
    }

    // Constructor: receive an array of rectangles
    public ArrayRectangles(Rectangle... rectangles) {
        if (rectangles == null || rectangles.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        rectangleArray = new Rectangle[rectangles.length];
        System.arraycopy(rectangles, 0, rectangleArray, 0, rectangles.length);
    }

    // Add rectangle at the nearest empty place
    public boolean addRectangle(Rectangle rectangle) {
        for (int i = 0; i < rectangleArray.length; i++) {
            if (rectangleArray[i] == null) {
                rectangleArray[i] = rectangle;
                return true;
            }
        }
        return false; // no free space
    }

    // Return actual number of elements (non-null)
    public int size() {
        int count = 0;
        for (Rectangle r : rectangleArray) {
            if (r != null) count++;
        }
        return count;
    }

    // Return index of first rectangle with maximum area
    public int indexMaxArea() {
        if (size() == 0) return -1;
        int maxIndex = -1;
        double maxArea = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < rectangleArray.length; i++) {
            Rectangle r = rectangleArray[i];
            if (r != null && r.area() > maxArea) {
                maxArea = r.area();
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Return index of first rectangle with minimum perimeter
    public int indexMinPerimeter() {
        if (size() == 0) return -1;
        int minIndex = -1;
        double minPerimeter = Double.POSITIVE_INFINITY;
        for (int i = 0; i < rectangleArray.length; i++) {
            Rectangle r = rectangleArray[i];
            if (r != null && r.perimeter() < minPerimeter) {
                minPerimeter = r.perimeter();
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Return number of squares in the array
    public int numberSquares() {
        int count = 0;
        for (Rectangle r : rectangleArray) {
            if (r != null && r.isSquare()) count++;
        }
        return count;
    }
}
