package com.epam.rd.autotasks;

public class CountDownTask implements Task{

    private int countdown;

    public CountDownTask(int value) {
        if (value < 0) {
            this.countdown = 0;
        } else {
            this.countdown = value;  // Fixed: was missing else clause
        }
    }

    public int getValue() {
        return countdown;
    }


    @Override
    public void execute() {
        if (countdown > 0) {
            countdown--;
        }
    }

    @Override
    public boolean isFinished() {
        return countdown == 0;
    }
}
