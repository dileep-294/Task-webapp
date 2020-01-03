package com.dileep;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskFileRepository implements TaskRepository {

    private static final String TASKS_JSON_FILE = "/home/dileepj/IdeaProjects/tasks/tasks-webapp/src/main/java/com/dileep/TASKS.JSON";

    ObjectMapper objectMapper = new ObjectMapper();

    List<Task> tasks;

    public TaskFileRepository() {
        tasks = readFromFile();
    }

    public void writeToFile(List<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(TASKS_JSON_FILE), tasks);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Task> readFromFile() {
        final File file = new File(TASKS_JSON_FILE);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, TaskList.class);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        writeToFile(tasks);
    }

    @Override
    public List<Task> display() {

        return readFromFile();
    }

    @Override
    public boolean delete(int taskId) {

        Task task1 = null;
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                tasks.remove(task);
                //task1=task;
                return true;
            }
        }
        return false;
    }

    @Override
    public Task searchByTaskId(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> listByStatus(Status status) {
        List<Task> taskList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus().equals(status)) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    @Override
    public void updateStatus(Status status, int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setStatus(status);
                writeToFile(tasks);
            }
        }

    }

    @Override
    public int getTotalCount() {
        return tasks.size();
    }

    @Override
    public List<Task> getPendingTasks() {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus().equals(Status.valueOf("Created")) || task.getStatus().equals(Status.valueOf("InProgress"))) {
                pendingTasks.add(task);
            }
        }
        Collections.sort(pendingTasks);
        return pendingTasks;
    }

    public List<Task> getTodayTasks() {
        List<Task> todaysTasks = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        for (Task task : tasks) {
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
