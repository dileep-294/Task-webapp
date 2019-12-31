package com.dileep;

import java.util.List;

public interface TaskRepository {
    void addTask(task task);

    List<task> display();

    void delete(int taskId);

    task searchByTaskId(int taskId);

    List<task>  listByStatus(Status status);

    void updateStatus(Status status, int taskId);

    int getTotalCount();

    List<task> getPendingTasks();

    List<task> getTodaysTasks();
}
