package com.epam.rd.autotasks;


class LoopStatements {
    public static int sumOfFibonacciNumbers(int n) {
        if (n < 0) { // allow zero
            throw new IllegalArgumentException();
        }

        int sum = 0;
        int a = 0;
        int b = 1;

        for (int i = 1; i <= n; i++) {
            sum += a;
            int next = a + b;
            a = b;
            b = next;
        }

        return sum;
    }
}
