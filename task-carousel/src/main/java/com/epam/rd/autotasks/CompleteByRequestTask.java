package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {
    private boolean completeRequested = false;
    private boolean finished = false;
    public CompleteByRequestTask() {
    }

    @Override
    public void execute() {
        if (completeRequested && !isFinished()) {
            finished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    public void complete() {
        completeRequested = true;
    }
}
