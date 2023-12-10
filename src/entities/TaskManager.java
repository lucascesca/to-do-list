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
/*        if (task.getId() > tasks.size()) {
            task.setId(tasks.size() + 1);
        }*/
        tasks.add(task);
        setTaskId(tasks.size());
    }

    public void removeTask(int taskId) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == taskId) {
                iterator.remove();
                setTaskId(taskId);
                break;
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

    public List<Task> getTasks() {
        return tasks;
    }
}
