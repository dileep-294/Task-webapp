package com.dileep;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {
    List<Task> taskList = new ArrayList<Task>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public List<Task> display() {
        return taskList;
    }

    public boolean delete(int taskId) {

        for (Task task : taskList) {
            if (task.getTaskId() == taskId) {
                taskList.remove(task);
                return true;
            }
        }
        return false;
    }

    public Task searchByTaskId(int taskId) {
        for (Task task : taskList) if (task.getTaskId() == taskId) return task;
        return null;
    }

    public List<Task> listByStatus(Status status) {
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getStatus().equals(status))
                tempTaskList.add(task);
        }
        return tempTaskList;
    }

    public void updateStatus(Status status, int taskId) {
        for (Task task : taskList) {
            if (task.getTaskId() == taskId) {
                task.setStatus(status);
            }
        }
    }

    public int getTotalCount() {
        return taskList.size();
    }

    public List<Task> getPendingTasks() {
        List<Task> taskList1 = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getStatus().equals(Status.valueOf("Created")) || task.getStatus().equals(Status.valueOf("InProgress"))) {
                taskList1.add(task);
            }
        }
        return taskList1;
    }

    public List<Task> getTodayTasks() {
        List<Task> todaysTasks = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        for (Task task : taskList) {
            try {
                if (task.getDueDate().equals(dateFormat.parse(today))) {
                    todaysTasks.add(task);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return todaysTasks;
    }
}
