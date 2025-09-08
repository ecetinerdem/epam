package com.epam.rd.autotasks.triangle;

class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException();
        }

        if (Double.compare(a.getX(),b.getX()) == 0 && Double.compare(a.getY(), b.getY()) == 0) {
            throw new IllegalArgumentException();
        }

        if (Double.compare(b.getX(),c.getX()) == 0 && Double.compare(b.getY(), c.getY()) == 0) {
            throw new IllegalArgumentException();
        }

        if (Double.compare(a.getX(),c.getX()) == 0 && Double.compare(a.getY(), c.getY()) == 0) {
            throw new IllegalArgumentException();
        }

        double crossProduct = (b.getX() - a.getX()) * (c.getY() - a.getY()) -
                (b.getY() - a.getY()) * (c.getX() - a.getX());

        if (Double.compare(Math.abs(crossProduct), 0.0) == 0) {
            throw new IllegalArgumentException();
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }



    public double area() {
        // Using the cross product formula: |AB × AC| / 2
        double crossProduct = (b.getX() - a.getX()) * (c.getY() - a.getY()) -
                (b.getY() - a.getY()) * (c.getX() - a.getX());
        return Math.abs(crossProduct) / 2.0;
    }

    public Point centroid() {
        // Centroid is the average of the three vertices
        double centroidX = (a.getX() + b.getX() + c.getX()) / 3.0;
        double centroidY = (a.getY() + b.getY() + c.getY()) / 3.0;
        return new Point(centroidX, centroidY);
    }

}
