package com.epam.rd.autotasks.max;

public class MaxMethod {
    public static int max(int[] values) {
        int curentMax = Integer.MIN_VALUE;

        for (int value : values) {
            if (value > curentMax) {
                curentMax = value;
            }
        };
        return curentMax;

    }
}
