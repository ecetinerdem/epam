package com.epam.rd.autotasks;


class LoopStatements {
    public static int sumOddDigits(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        String nstring = String.valueOf(n);
        int sum = 0;

        for (char num : nstring.toCharArray()) {
            int number = num - '0';

            if (number % 2 != 0) {
                sum += number;
            }
        }

        return sum;
    }
}
