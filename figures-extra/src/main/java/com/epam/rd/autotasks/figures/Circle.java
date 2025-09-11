package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        if (center == null) {
            throw new IllegalArgumentException("Center cannot be null");
        }

        if (radius <= EPSILON) {
            throw new IllegalArgumentException("Circle is degenerate (zero or negative radius)");
        }

        this.center = center;
        this.radius = radius;
    }

    @Override
    public Point centroid() {
        // Centroid of circle is its center
        return new Point(center.getX(), center.getY());
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Circle)) {
            return false;
        }

        Circle other = (Circle) figure;

        return pointsEqual(this.center, other.center) &&
                isEqual(this.radius, other.radius);
    }

    private boolean pointsEqual(Point p1, Point p2) {
        return isEqual(p1.getX(), p2.getX()) && isEqual(p1.getY(), p2.getY());
    }
}
