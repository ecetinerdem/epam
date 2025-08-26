package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();

        int hour = (seconds / 3600) % 24;
        int minute = (seconds % 3600) / 60;
        int second = seconds % 60;

        String time = String.format("%01d:%02d:%02d",hour,minute,second);

        System.out.println(time);


    }
}
