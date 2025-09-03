package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CycleSwap {
    static void cycleSwap(int[] array) {

        if (array.length <= 1) {
            return;
        }

        int lastElmenet = array[array.length - 1];

        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = lastElmenet;

    }

    static void cycleSwap(int[] array, int shift) {
        if (array.length <= 1 || shift == 0) {
            return;
        }

        shift = shift % array.length;

        if (shift == 0) {
            return;
        }

        int[] temp = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int newPosition = (i + shift) % array.length;
            temp[newPosition] = array[i];
        }

        System.arraycopy(temp, 0, array, 0, array.length);
    }
}
