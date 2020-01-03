package com.dileep;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);

    List<Task> display();

    boolean delete(int taskId);

    Task searchByTaskId(int taskId);

    List<Task> listByStatus(Status status);

    void updateStatus(Status status, int taskId);

    int getTotalCount();

    List<Task> getPendingTasks();

    List<Task> getTodayTasks();
}
