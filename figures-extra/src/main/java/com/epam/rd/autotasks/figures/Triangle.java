package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }

        this.a = a;
        this.b = b;
        this.c = c;

        if (getArea() <= EPSILON) {
            throw new IllegalArgumentException("Triangle is degenerate (zero area)");
        }
    }

    private double getArea() {
        // Using cross product formula: Area = 0.5 * |AB × AC|
        double ab_x = b.getX() - a.getX();
        double ab_y = b.getY() - a.getY();
        double ac_x = c.getX() - a.getX();
        double ac_y = c.getY() - a.getY();

        return 0.5 * Math.abs(ab_x * ac_y - ab_y * ac_x);
    }

    @Override
    public Point centroid() {
        // Centroid of triangle is the average of its vertices
        double cx = (a.getX() + b.getX() + c.getX()) / 3.0;
        double cy = (a.getY() + b.getY() + c.getY()) / 3.0;
        return new Point(cx, cy);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Triangle)) {
            return false;
        }

        Triangle other = (Triangle) figure;
        Point[] thisVertices = {a, b, c};
        Point[] otherVertices = {other.a, other.b, other.c};

        // Check all possible permutations
        return isPermutation(thisVertices, otherVertices);
    }

    private boolean isPermutation(Point[] arr1, Point[] arr2) {
        boolean[] used = new boolean[3];

        for (Point p1 : arr1) {
            boolean found = false;
            for (int i = 0; i < 3; i++) {
                if (!used[i] && pointsEqual(p1, arr2[i])) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    private boolean pointsEqual(Point p1, Point p2) {
        return isEqual(p1.getX(), p2.getX()) && isEqual(p1.getY(), p2.getY());
    }
}
