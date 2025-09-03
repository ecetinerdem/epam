package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class SumOfPrevious {

    public static void main(String[] args) {
        int[] array = new int[]{1, -1, 0, 4, 6, 10, 15, 25};

        System.out.println(Arrays.toString(getSumCheckArray(array)));
    }

    public static boolean[] getSumCheckArray(int[] array){

        boolean[] boolList = new boolean[array.length];

        if (array.length < 3) {
            // If there are less than 3 elements, no sum check is possible
            return boolList; // all false
        }

        // First two elements cannot satisfy the condition
        boolList[0] = false;
        boolList[1] = false;

        for (int i = 2; i < array.length; i++) {
            boolList[i] = (array[i] == array[i - 1] + array[i - 2]);
        }
        return boolList;
    }
}
