package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

public class Sprint {

    private final int capacity;
    private final int ticketsLimit;
    private final Ticket[] tickets;
    private int ticketCount;
    private int totalEstimate;

    public Sprint(int capacity, int ticketsLimit) {

        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
        this.tickets = new Ticket[ticketsLimit];
        this.ticketCount = 0;
        this.totalEstimate = 0;
    }

    public boolean addUserStory(UserStory userStory) {
        if (!canAddTicket(userStory)) {
            return false;
        }
        UserStory[] dependencies = userStory.getDependencies();
        for (UserStory dependency : dependencies) {
            if (dependency != null && !dependency.isCompleted()) {
                if (!isTicketInSprint(dependency)) {
                    return false; // Uncompleted dependency not in sprint
                }
            }
        }

        // Add the user story
        tickets[ticketCount] = userStory;
        ticketCount++;
        totalEstimate += userStory.getEstimate();
        return true;
    }

    private boolean isTicketInSprint(Ticket ticket) {
        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i] == ticket) {
                return true;
            }
        }
        return false;
    }

    private boolean canAddTicket(Ticket ticket) {
        // Check basic conditions
        if (ticket == null) {
            return false;
        }

        if (ticket.isCompleted()) {
            return false;
        }

        if (ticketCount >= ticketsLimit) {
            return false;
        }

        if (totalEstimate + ticket.getEstimate() > capacity) {
            return false;
        }

        return true;
    }

    public boolean addBug(Bug bugReport) {
        if (!canAddTicket(bugReport)) {
            return false;
        }

        // Add the bug
        tickets[ticketCount] = bugReport;
        ticketCount++;
        totalEstimate += bugReport.getEstimate();
        return true;
    }

    public Ticket[] getTickets() {
        Ticket[] copy = new Ticket[ticketCount];
        System.arraycopy(tickets, 0, copy, 0, ticketCount);
        return copy;
    }

    public int getTotalEstimate() {
        return totalEstimate;
    }
}
