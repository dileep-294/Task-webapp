package com.dileep;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskFileRepository  implements TaskRepository{

    private static final String TASKS_JSON_FILE ="/home/dileepj/IdeaProjects/TASK_Management/src/com/dileep/TASKS.JSON";

    ObjectMapper objectMapper = new ObjectMapper();

    List<task>tasks;

    public TaskFileRepository(){
        tasks = readFromFile();
    }

    public void writeToFile(List<task>tasks){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(TASKS_JSON_FILE),tasks);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<task> readFromFile(){
        final File  file = new File(TASKS_JSON_FILE);
        if(file.exists()){
            try{
                return objectMapper.readValue(file,TaskList.class);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        else{
            return new ArrayList<>();
        }
    }

    @Override
    public void addTask(task task) {
        tasks.add(task);
        writeToFile(tasks);
    }

    @Override
    public List<task> display() {
        return readFromFile();
    }

    @Override
    public void delete(int taskId) {
        try {
            task task1 = null;
            for (com.dileep.task task : tasks) {
                if (task.getTaskId() == taskId) {
                    //tasks.remove(task);
                    task1=task;
                    //writeToFile(tasks);
                }

            }
            tasks.remove(task1);
            //writeToFile(tasks);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public task searchByTaskId(int taskId) {
        for(com.dileep.task task : tasks){
            if(task.getTaskId()==taskId){
                return task;
            }
        }
        return null;
    }

    @Override
    public List<task> listByStatus(Status status) {
        List<task> taskList = new ArrayList<>();
        for(com.dileep.task task : tasks){
            if(task.getStatus().equals(status)){
                taskList.add(task);
            }
        }
        return taskList;
    }

    @Override
    public void updateStatus(Status status, int taskId) {
        for(com.dileep.task task : tasks){
            if(task.getTaskId() == taskId){
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
    public List<task> getPendingTasks() {
        List<task> pendingTasks = new ArrayList<>();
        for(com.dileep.task task : tasks){
            if(task.getStatus().equals(Status.valueOf("Created")) || task.getStatus().equals(Status.valueOf("InProgress"))){
                pendingTasks.add(task);
            }
        }
        Collections.sort(pendingTasks);
        return pendingTasks;
    }
    public List<task> getTodaysTasks(){
        List<task> todaysTasks = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        for(com.dileep.task task : tasks){
            try {
                if(task.getDueDate().equals(dateFormat.parse(today))){
                    todaysTasks.add(task);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return todaysTasks;
    }
}
