package com.dileep;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository{
    ArrayList<task> taskList=new ArrayList<task>();


    public void addTask(task task){
        taskList.add(task);
    }

    public List<task> display(){
        return taskList;
    }

    public void delete(int taskId){
        //try {
        //System.out.println("Entered to Inmemory");
            for (com.dileep.task task : taskList) {
                if (task.getTaskId() == taskId) {
                    taskList.remove(task);
                }
            //}
        } /*catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public task searchByTaskId(int taskId){
        for(com.dileep.task task:taskList) if (task.getTaskId() == taskId) return task;
        return null;
    }

    public List<task>  listByStatus(Status status){
        ArrayList<task>tempTaskList = new ArrayList<>();
        for(com.dileep.task task:taskList){
            if(task.getStatus().equals(status))
                tempTaskList.add(task);
        }
        return tempTaskList;
    }

    public void updateStatus(Status status, int taskId){
        for(com.dileep.task task:taskList){
            if(task.getTaskId()==taskId){
                task.setStatus(status);
            }
        }
    }
    public int getTotalCount(){
        return taskList.size();
    }
    public List<task> getPendingTasks(){
        List<task> taskList1 = new ArrayList<>();
        for(com.dileep.task task : taskList){
            if(task.getStatus().equals(Status.valueOf("Created")) || task.getStatus().equals(Status.valueOf("InProgress"))){
                taskList1.add(task);
            }
        }
        return taskList1;
    }

    public List<task> getTodaysTasks(){
        List<task> todaysTasks = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        for(com.dileep.task task : taskList){
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
