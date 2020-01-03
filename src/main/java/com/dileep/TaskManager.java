package com.dileep;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TaskManager {
    //int idCounter=1;

    TaskRepository repository = new TaskDataBase();

    public TaskManager() {
    }

    public void addTask(String name, String description, Status status, Date date) throws SQLException {
        Random random = new Random();

        repository.addTask(new Task(random.nextInt(1000), name, description, date, status));
        //idCounter+=1;
    }

    public List<Task> display() {
        return repository.display();
    }

    public boolean delete(int taskId) {
        return repository.delete(taskId);

    }

    public Task searchByTaskId(int taskId) {
        return repository.searchByTaskId(taskId);
    }

    public List<Task> listByStatus(Status status) {
        return repository.listByStatus(status);
    }

    public void updateStatus(Status status, int taskId) {
        repository.updateStatus(status, taskId);

    }

    public int getTotalCount() {
        return repository.getTotalCount();
    }

    public List<Task> getPendingTasks() {
        return repository.getPendingTasks();
    }

    public List<Task> getTodaysTasks() {
        return repository.getTodayTasks();
    }
}
