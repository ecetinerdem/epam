package com.epam.rd.autotasks.godutch;

import java.util.Scanner;

public class GoDutch {

    public static void main(String[] args) {
        //Write code here

        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        if (amount < 0) {
            System.out.println("Bill total amount cannot be negative");
            return;
        }


        int friends = scanner.nextInt();
        if (friends <= 0) {
            System.out.println("Number of friends cannot be negative or zero");
            return;
        }

        int amountWithTip = amount + ((amount * 10) / 100);
        int paymentByEach = amountWithTip / friends;

        System.out.println(paymentByEach);
    }
}
