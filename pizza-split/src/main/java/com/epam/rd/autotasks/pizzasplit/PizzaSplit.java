package com.epam.rd.autotasks.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {
    public static void main(String[] args) {
        //Write a program, reading number of people and number of pieces per pizza and then
        //printing minimum number of pizzas to order to split all the pizzas equally and with no remainder

        Scanner scanner = new Scanner(System.in);
        int people = scanner.nextInt();
        int slices = scanner.nextInt();


        int gcd = gcd(people,slices);
        int lcm = (people * slices) / gcd;

        int pizzas = lcm / slices;
        System.out.println(pizzas);

    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
