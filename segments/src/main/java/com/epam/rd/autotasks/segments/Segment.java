package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {

    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException();
        }
        // compare by value, not by reference
        if (Double.compare(start.getX(), end.getX()) == 0 &&
                Double.compare(start.getY(), end.getY()) == 0) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
    }

    double length() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() -start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    Point middle() {
        double midX = (start.getX() + end.getX()) / 2.0;
        double midY = (start.getY() + end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    Point intersection(Segment another) {
        double x1 = start.getX(), y1 = start.getY();
        double x2 = end.getX(), y2 = end.getY();
        double x3 = another.start.getX(), y3 = another.start.getY();
        double x4 = another.end.getX(), y4 = another.end.getY();

        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominator == 0) {
            // parallel or collinear
            return null;
        }

        double px = ((x1 * y2 - y1 * x2) * (x3 - x4)
                - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4)
                - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        // check if px, py lies on both segments
        if (onSegment(px, py, x1, y1, x2, y2) && onSegment(px, py, x3, y3, x4, y4)) {
            return new Point(px, py);
        }

        return null;
    }

    private boolean onSegment(double px, double py, double x1, double y1, double x2, double y2) {
        return px >= Math.min(x1, x2) && px <= Math.max(x1, x2)
                && py >= Math.min(y1, y2) && py <= Math.max(y1, y2);
    }

}
