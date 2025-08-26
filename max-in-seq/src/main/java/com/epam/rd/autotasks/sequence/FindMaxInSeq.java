package com.epam.rd.autotasks.sequence;
import java.util.ArrayList;
import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        while (true) {
            int number = scanner.nextInt();
            if (number == 0) break;
            numbers.add(number);
        }

        int biggest = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > biggest) {
                biggest = numbers.get(i);
            }
        }
        return biggest;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        System.out.println(max());
    }
}
