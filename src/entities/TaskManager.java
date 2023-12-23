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
                tasksOrdered(tasks);
                return tasks;
        }
    }

    public int addTask(Task task) {
        if (hasDate(task)) {
            return 1;
        }
        else {
            tasks.add(task);
            return 0;
        }
    }

    public void removeTask(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                tasks.remove(tasks.get(i));
                tasksOrdered(tasks);
            }
        }
    }

    private void tasksOrdered(List<Task> list) {
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
