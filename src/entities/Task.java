package entities;

import entities.enums.Status;

import java.util.Date;

public class Task {
    private String description;
    private Date dueDate;
    private boolean completed;
    private Status status;

    public Task(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        status = Status.PENDING;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return description + " " + dueDate;
    }
}
