package entities;

import entities.enums.Status;

import java.util.*;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        tasksOrdered(tasks);
        return tasks;
    }

    public List<Task> getTasks(int listType) {
        tasksOrdered(tasks);
        List<Task> tempList;
        switch (listType) {
            case 1:
                tempList = tasks.stream().filter(x -> x.getStatus() == Status.DONE).toList();
                tasksOrdered(tempList);
                return tempList;
            case 2:
                tempList = tasks.stream().filter(x -> x.getStatus() == Status.ONGOING).toList();
                tasksOrdered(tempList);
                return tempList;
            case 3:
                tempList = tasks.stream().filter(x -> x.getStatus() == Status.PENDING).toList();
                tasksOrdered(tempList);
                return tempList;
            default:
                return tasks;
        }
    }

    public Task getSpecificTask(Task other) {
        for (Task task : tasks) {
            if (task.getDueDateTime().equals(other.getDueDateTime())) {
                return task;
            }
        }
        return other;
    }

    public boolean addTask(Task task) {
        if (hasDate(task)) {
            return false;
        }
        else {
            tasks.add(task);
            return true;
        }
    }

    public Task removeTask(int taskId) {
        Task task = null;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                task = tasks.remove(i);
                tasksOrdered(tasks);
            }
        }

        return task;
    }

    private void tasksOrdered(List<Task> list) {
        if (list.size() > 1) {
            tasks.sort(Comparator.comparing(Task::getDueDateTime));
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i + 1);
        }
    }

    public void changeTaskStatus(int id, int status) {
        id--;
        status--;
        Status[] s = Status.values();
        tasks.get(id).setStatus(s[status]);
    }

    public boolean hasDate(Task task) {
        // Verifies if the time and date already have a task
        for (Task t : tasks) {
            if (t.getDueDateTime().equals(task.getDueDateTime())) {
                return true;
            }
        }
        return false;
    }
}
