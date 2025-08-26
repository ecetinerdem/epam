package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Use Scanner methods to read input

        ArrayList<Integer>numbers = new ArrayList<>();
        while (true) {
            int number = scanner.nextInt();
            if (number == 0) break;
            numbers.add(number);
        }

        int total = 0;
        for (int i = 0; i < numbers.size(); i++) {
            total += numbers.get(i);
        }

        int avarage = total / numbers.size();

        System.out.println(avarage);
    }

}