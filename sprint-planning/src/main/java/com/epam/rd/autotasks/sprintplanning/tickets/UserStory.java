package com.epam.rd.autotasks.sprintplanning.tickets;

import java.util.Arrays;
import java.util.Objects;

public class UserStory extends Ticket {

    private final UserStory[] dependencies;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);

        this.dependencies = Objects.requireNonNullElseGet(dependsOn, () -> new UserStory[0]);


    }

    @Override
    public void complete() {
        for (UserStory dependency: dependencies) {
            if (dependency != null && !dependency.isCompleted()) {
                return;
            }
        }
        super.complete();
    }

    public UserStory[] getDependencies() {
        UserStory[] copy = new UserStory[dependencies.length];
        System.arraycopy(dependencies, 0, copy, 0, dependencies.length);
        return copy;
    }

    @Override
    public String toString() {
        return "[US " + getId() + "] " + getName();
    }
}
