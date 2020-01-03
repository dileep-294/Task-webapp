package com.dileep;

import java.util.Date;

public class Task implements Comparable<Task> {
    public int getTaskId() {
        return taskId;
    }

    public Task() {

    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    private int taskId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;


    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    private Date dueDate;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status st) {
        this.status = st;
    }

    public Task(int taskId, String name, String description, Date date, Status status) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueDate = date;

    }

    @Override
    public String toString() {
        return (
                " taskId : " + taskId +
                        " name : " + name +
                        " description : " + description +
                        " dueDate : " + dueDate +
                        " status : " + status);
    }

    @Override
    public int compareTo(Task task) {
        if (this.getDueDate().compareTo(task.getDueDate()) == 0) {
            if (this.getStatus().equals(Status.valueOf("InProgress")))
                return -1;
            if (this.getStatus().equals(Status.valueOf("Created")))
                return 1;
        } else {
            if (this.getDueDate().compareTo(getDueDate()) < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
