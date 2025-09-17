package com.epam.rd.autotasks;



public enum Direction {
    N(0), NE(45), E(90), SE(135),
    S(180), SW(225), W(270), NW(315);

    private final int degrees;

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private static int normalize(int degrees) {
        int d = degrees % 360;
        return d < 0 ? d + 360 : d;
    }

    public static Direction ofDegrees(int degrees) {
        int normalized = normalize(degrees);
        for (Direction dir : values()) {
            if (dir.degrees == normalized) {
                return dir;
            }
        }
        return null;
    }

    public static Direction closestToDegrees(int degrees) {
        int normalized = normalize(degrees);
        Direction closest = null;
        int minDiff = Integer.MAX_VALUE;

        for (Direction dir : values()) {
            int diff = Math.abs(dir.degrees - normalized);
            diff = Math.min(diff, 360 - diff); // shortest angular distance

            if (diff < minDiff) {
                minDiff = diff;
                closest = dir;
            }
        }
        return closest;
    }

    public Direction opposite() {
        int oppositeDeg = (this.degrees + 180) % 360;
        return ofDegrees(oppositeDeg);
    }

    public int differenceDegreesTo(Direction direction) {
        int diff = Math.abs(this.degrees - direction.degrees);
        return Math.min(diff, 360 - diff);
    }
}
