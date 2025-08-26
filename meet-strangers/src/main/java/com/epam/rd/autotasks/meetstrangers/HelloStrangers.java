package com.epam.rd.autotasks.meetstrangers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        //Write a program, asks for a number - amount of strangers to meet.
        //Then reads stranger names line by line and prints line by line "Hello, ...".
        //System.out.print("Enter a number of strangers to meet: ");
        Scanner scanner = new Scanner(System.in);
        int strangerstoMeet = scanner.nextInt();
        scanner.nextLine();

        if (strangerstoMeet < 0) {
            System.out.println("Seriously? Why so negative?");
        } else if (strangerstoMeet == 0) {
            System.out.println("Oh, it looks like there is no one here");
        } else {
            List<String> names = new ArrayList<>();
            for(int i = 0; i < strangerstoMeet; i++) {
                String name = scanner.nextLine();
                names.add(name);
            }

            for (String name : names) {
                System.out.println("Hello, " + name);
            }
        }
    }
}
