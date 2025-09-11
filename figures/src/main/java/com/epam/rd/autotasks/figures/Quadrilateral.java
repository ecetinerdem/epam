package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        Triangle triangle1 = new Triangle(a, b, c);
        Triangle triangle2 = new Triangle(a, c, d);
        return triangle1.area() + triangle2.area();
    }

    @Override
    public String pointsToString() {
        return a.toString() + b.toString() + c.toString() + d.toString();
    }

    @Override
    public Point leftmostPoint() {
        Point leftmost = a;
        if (b.getX() < leftmost.getX()) {
            leftmost = b;
        }
        if (c.getX() < leftmost.getX()) {
            leftmost = c;
        }
        if (d.getX() < leftmost.getX()) {
            leftmost = d;
        }
        return leftmost;
    }

    @Override
    public String toString() {
        return "Quadrilateral[" + pointsToString() + "]";
    }
}
