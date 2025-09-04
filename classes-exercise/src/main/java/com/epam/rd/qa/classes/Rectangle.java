package com.epam.rd.qa.classes;

import java.util.Objects;

public class Rectangle {

    private double sideA;
    private double sideB;

    // Default constructor (sideA = 4, sideB = 3)
    public Rectangle() {
        this.sideA = 4;
        this.sideB = 3;
    }

    // Constructor with two parameters
    public Rectangle(double sideA, double sideB) {
        validateSide(sideA);
        validateSide(sideB);
        this.sideA = sideA;
        this.sideB = sideB;
    }

    // Constructor with one parameter (square)
    public Rectangle(double side) {
        validateSide(side);
        this.sideA = side;
        this.sideB = side;
    }

    // Validation method
    private void validateSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side length must be greater than zero");
        }
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double area() {
        return sideA * sideB;
    }

    public double perimeter() {
        return (sideA + sideB) * 2;
    }

    public boolean isSquare() {
        return Double.compare(sideA, sideB) == 0;
    }

    public void replaceSides() {
        double temp = sideA;
        sideA = sideB;
        sideB = temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.sideA, sideA) == 0 &&
                Double.compare(rectangle.sideB, sideB) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                '}';
    }
}
