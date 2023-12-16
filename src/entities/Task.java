package entities;

import entities.enums.Status;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int id;
    private String description;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private boolean completed;
    private Status status;

    public Task() {}

    public Task(String description, LocalDate dueDate, LocalTime dueTime) {
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        status = Status.PENDING;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDateTime() {
        return dueDate.atTime(dueTime);
    }

    public boolean isCompleted() {
        return completed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return id + ": " + description + " " + dueDate.format(fmt) + " " + dueTime + " " + status;
    }
}
