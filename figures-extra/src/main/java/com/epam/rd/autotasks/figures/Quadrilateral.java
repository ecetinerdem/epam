package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {
    private Point a, b, c, d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        if (!isConvex()) {
            throw new IllegalArgumentException("Quadrilateral is not convex");
        }

        if (getArea() <= EPSILON) {
            throw new IllegalArgumentException("Quadrilateral is degenerate (zero area)");
        }
    }

    private boolean isConvex() {
        // A quadrilateral is convex if all cross products have the same sign
        Point[] vertices = {a, b, c, d};
        int n = 4;
        boolean positive = false, negative = false;

        for (int i = 0; i < n; i++) {
            Point p1 = vertices[i];
            Point p2 = vertices[(i + 1) % n];
            Point p3 = vertices[(i + 2) % n];

            double cross = crossProduct(p1, p2, p3);

            // Check for collinear points (cross product near zero)
            if (Math.abs(cross) < EPSILON) {
                return false;
            }

            if (cross > EPSILON) positive = true;
            else if (cross < -EPSILON) negative = true;

            if (positive && negative) return false;
        }

        return true;
    }

    private double crossProduct(Point p1, Point p2, Point p3) {
        double v1x = p2.getX() - p1.getX();
        double v1y = p2.getY() - p1.getY();
        double v2x = p3.getX() - p2.getX();
        double v2y = p3.getY() - p2.getY();

        return v1x * v2y - v1y * v2x;
    }

    private double getArea() {
        // Using shoelace formula
        double sum = 0;
        Point[] vertices = {a, b, c, d};

        for (int i = 0; i < 4; i++) {
            Point curr = vertices[i];
            Point next = vertices[(i + 1) % 4];
            sum += curr.getX() * next.getY() - next.getX() * curr.getY();
        }

        return Math.abs(sum) / 2.0;
    }

    @Override
    public Point centroid() {
        // Area-weighted centroid of quadrilateral
        // Divide into two triangles and compute weighted average
        Triangle t1 = new Triangle(a, b, c);
        Triangle t2 = new Triangle(a, c, d);

        double area1 = getTriangleArea(a, b, c);
        double area2 = getTriangleArea(a, c, d);
        double totalArea = area1 + area2;

        Point c1 = t1.centroid();
        Point c2 = t2.centroid();

        double cx = (c1.getX() * area1 + c2.getX() * area2) / totalArea;
        double cy = (c1.getY() * area1 + c2.getY() * area2) / totalArea;

        return new Point(cx, cy);
    }

    private double getTriangleArea(Point p1, Point p2, Point p3) {
        double ab_x = p2.getX() - p1.getX();
        double ab_y = p2.getY() - p1.getY();
        double ac_x = p3.getX() - p1.getX();
        double ac_y = p3.getY() - p1.getY();

        return 0.5 * Math.abs(ab_x * ac_y - ab_y * ac_x);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Quadrilateral)) {
            return false;
        }

        Quadrilateral other = (Quadrilateral) figure;
        Point[] thisVertices = {a, b, c, d};
        Point[] otherVertices = {other.a, other.b, other.c, other.d};

        return isPermutation(thisVertices, otherVertices);
    }

    private boolean isPermutation(Point[] arr1, Point[] arr2) {
        boolean[] used = new boolean[4];

        for (Point p1 : arr1) {
            boolean found = false;
            for (int i = 0; i < 4; i++) {
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