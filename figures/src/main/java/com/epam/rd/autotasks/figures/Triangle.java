package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private Point a;
    private  Point b;
    private  Point c;

    public Triangle(Point c, Point a, Point b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }

    @Override
    public double area() {
        double area = Math.abs(
                        a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())
                    ) / 2.0;
        return area;
    }

    @Override
    public String pointsToString() {
        return c.toString() + a.toString() + b.toString();
    }

    @Override
    public Point leftmostPoint() {
        Point leftMost = a;

        if (b.getX() < leftMost.getX()) {
            leftMost = b;
        }
        if (c.getX() < leftMost.getX()) {
            leftMost = c;
        }

        return leftMost;
    }

    @Override
    public String toString() {
        return "Triangle[" + pointsToString() + "]";
    }
}
