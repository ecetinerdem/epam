package com.epam.rd.autotasks.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public class IntArrayUtil {

    public static int maximumDistance(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int max = Arrays.stream(array).max().orElse(0);
        int left = 0;
        int right = array.length - 1;

        // Find first occurrence from left
        while (left < array.length && array[left] != max) {
            left++;
        }

        // Find last occurrence from right
        while (right >= 0 && array[right] != max) {
            right--;
        }

        return right - left;
    }

	public static void main(String[] args) {
		{
			int[] array = null;
			System.out.println("result = " + maximumDistance(array));
		}
		{
			int[] array = new int[] {};
			System.out.println("result = " + maximumDistance(array));
		}
		{
			int[] array = new int[] { 4, 100, 3, 4 };
			System.out.println("result = " + maximumDistance(array));
		}
		{
			int[] array = new int[] { 5, 50, 50, 4, 5 };
			System.out.println("result = " + maximumDistance(array));
		}
		{
			int[] array = new int[] { 5, 350, 350, 4, 350 };
			System.out.println("result = " + maximumDistance(array));
		}
		{
			int[] array = new int[] { 10, 10, 10, 10, 10 };
			System.out.println("result = " + maximumDistance(array));
		}
	}

}
