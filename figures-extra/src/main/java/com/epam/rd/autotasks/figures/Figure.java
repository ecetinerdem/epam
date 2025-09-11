package com.epam.rd.autotasks.figures;

abstract class Figure {
    protected static final double EPSILON = 1e-9;

    public abstract Point centroid();
    public abstract boolean isTheSame(Figure figure);

    protected static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}
