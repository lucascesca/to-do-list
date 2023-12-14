package entities;

import entities.enums.Status;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static int classId = 1;
    private int id;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;
    private Status status;

    public Task() {}

    public Task(String description, LocalDateTime dueDate) {
        this.id = classId++;
        this.description = description;
        this.dueDate = dueDate;
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

    public LocalDateTime getDueDate() {
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
        return id + ": " + description + " " + dueDate.format(fmt);
    }
}
