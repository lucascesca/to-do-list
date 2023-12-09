package entities;

import entities.enums.Status;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static int classid = 1;
    private int id;
    private String description;
    private Date dueDate;
    private boolean completed;
    private Status status;

    public Task(String description, Date dueDate) {
        this.id = classid++;
        this.description = description;
        this.dueDate = dueDate;
        status = Status.PENDING;
    }

    public int getId() {
        return id;
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
        return id + ": " + description + " " + sdf.format(dueDate);
    }

}
