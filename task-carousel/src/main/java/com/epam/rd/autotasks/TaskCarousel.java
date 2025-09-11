package com.epam.rd.autotasks;

class TaskCarousel {
    private final Task[] tasks;
    private final int capacity;
    private int size = 0;
    private int currentIndex = 0;

    public TaskCarousel(int capacity) {
        this.capacity = capacity;
        this.tasks = new Task[capacity];
    }

    public boolean addTask(Task task) {
        // Check if task is null
        if (task == null) {
            return false;
        }

        // Check if task is already finished
        if (task.isFinished()) {
            return false;
        }

        // Check if carousel is full
        if (isFull()) {
            return false;
        }

        // Add task to the first available slot
        for (int i = 0; i < capacity; i++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                size++;
                break;
            }
        }

        return true;
    }

    public boolean execute() {
        if (isEmpty()) {
            return false;
        }

        // Find the next non-null task starting from currentIndex
        int startIndex = currentIndex;
        do {
            if (tasks[currentIndex] != null) {
                Task currentTask = tasks[currentIndex];

                // Execute the task
                currentTask.execute();

                // Move to next index for next execution
                currentIndex = (currentIndex + 1) % capacity;

                // If task is finished after execution, remove it
                if (currentTask.isFinished()) {
                    removeTaskAt(findTaskIndex(currentTask));
                }

                return true;
            }

            currentIndex = (currentIndex + 1) % capacity;
        } while (currentIndex != startIndex);

        return false;
    }

    private int findTaskIndex(Task task) {
        for (int i = 0; i < capacity; i++) {
            if (tasks[i] == task) {
                return i;
            }
        }
        return -1;
    }

    private void removeTaskAt(int index) {
        if (index >= 0 && index < capacity && tasks[index] != null) {
            tasks[index] = null;
            size--;

            // Adjust currentIndex if we removed the task at or before currentIndex
            if (size > 0) {
                // Find next valid task index
                while (size > 0 && tasks[currentIndex] == null) {
                    currentIndex = (currentIndex + 1) % capacity;
                }
            } else {
                currentIndex = 0;
            }
        }
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}