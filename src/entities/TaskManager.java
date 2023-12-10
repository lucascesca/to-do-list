package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
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

    public boolean hasId(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
