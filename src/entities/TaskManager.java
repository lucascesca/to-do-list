package entities;

import entities.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getTasks(int listType) {
/*        if (listType == 1) {
            return tasks.stream().filter(x -> x.getStatus() == Status.DONE).toList();
        }
        else if (listType == 2) {
            return tasks.stream().filter(x -> x.getStatus() == Status.ONGOING).toList();
        }
        else
            return tasks.stream().filter(x -> x.getStatus() == Status.PENDING).toList();*/

        return switch (listType) {
            case 1 -> tasks.stream().filter(x -> x.getStatus() == Status.DONE).toList();
            case 2 -> tasks.stream().filter(x -> x.getStatus() == Status.ONGOING).toList();
            case 3 -> tasks.stream().filter(x -> x.getStatus() == Status.PENDING).toList();
            default -> tasks;
        };
    }

    public void addTask(Task task) {
        tasks.add(task);
        setTaskId(tasks.size());
    }

    public void removeTask(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                tasks.remove(tasks.get(i));
                setTaskId(taskId);
            }
        }
    }

    private void setTaskId(int id) {
        for (int i = tasks.size() - 1; i >= 0; i--) {
            Task task = tasks.get(i);
            if (task.getId() > id) {
                task.setId(task.getId() - 1);
            }
        }
    }

    public void changeTaskStatus(int id, int status) {
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
