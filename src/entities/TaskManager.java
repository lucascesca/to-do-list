package entities;

import entities.enums.Status;

import java.util.*;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getTasks(int listType) {
        return switch (listType) {
            case 1 -> tasks.stream().filter(x -> x.getStatus() == Status.DONE).toList();
            case 2 -> tasks.stream().filter(x -> x.getStatus() == Status.ONGOING).toList();
            case 3 -> tasks.stream().filter(x -> x.getStatus() == Status.PENDING).toList();
            default -> tasks;
        };
    }

    public void addTask(Task task) {
        tasks.add(task);
        tasksOrdered();
    }

    public void removeTask(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                tasks.remove(tasks.get(i));
                tasksOrdered();
            }
        }
    }

    private void tasksOrdered() {
        tasks.sort(Comparator.comparing(Task::getDueDateTime));
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }
    }

    public void changeTaskStatus(int id, int status) {
        id--;
        status--;
        Status[] s = Status.values();
        tasks.get(id).setStatus(s[status]);
    }

    public boolean hasId(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
