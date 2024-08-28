package juno.manager;

import juno.task.Task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    public ArrayList<Task> getTasksArray() {
        return this.tasks;
    }

    public boolean isDuplicateTask(String taskDescription) {
        for (Task task : this.tasks) {
            if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                return true;
            }
        }
        return false;
    }
}
