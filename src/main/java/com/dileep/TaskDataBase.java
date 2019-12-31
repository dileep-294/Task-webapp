package com.dileep;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDataBase implements TaskRepository{

    String url;
    String username;
    String password;
    private static Connection con;
    private static Statement stmt;

    public TaskDataBase(){

        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getConnection() throws SQLException {

        url = "jdbc:mysql://localhost:3306/taskdb";

        username ="dileep";

        password = "Dileep@123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        con=DriverManager.getConnection(url,username,password);

    }

    @Override
    public void addTask(task task){

        try {
            stmt = con.createStatement();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = simpleDateFormat.format(task.getDueDate());

            String q="insert into task values("+task.getTaskId()+",\""+task.getName()+"\",\""+task.getDescription()+"\",\""+task.getStatus()
                    +"\",\""+currentDate+"\")";
           // System.out.println(q);

            stmt.executeUpdate(q);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        }

    @Override
    public void delete(int taskID){
        try {

            stmt = con.createStatement();

            stmt.executeUpdate("DELETE FROM task WHERE taskId="+taskID);

        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<task> display() {
        try {

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from task");

            return createTaskList(rs);

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<task> createTaskList(ResultSet rs){

        List<task> taskList = new ArrayList<>();

            try {
                while(rs.next()){
                    task task = new task();
                    task.setTaskId(rs.getInt(1));
                    task.setName(rs.getString(2));
                    task.setDescription(rs.getString(3));
                    task.setStatus(Status.valueOf(rs.getString(4)));
                    task.setDueDate(rs.getDate(5));
                     taskList.add(task);
                }
                return taskList;
            }

            catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
    }

    @Override
    public task searchByTaskId(int taskId){
        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from task where taskId=" + taskId);

            if (rs.next()) {
                task task = new task();
                task.setTaskId(rs.getInt(1));
                task.setName(rs.getString(2));
                task.setDescription(rs.getString(3));
                task.setStatus(Status.valueOf(rs.getString(4)));
                task.setDueDate(rs.getDate(5));
                //System.out.println(task);
                return task;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<task> listByStatus(Status status) {
        try {
            stmt = con.createStatement();

            System.out.println("select * from task where status="+ status);

            ResultSet rs = stmt.executeQuery("select * from task where status='"+status+"'");

           return createTaskList(rs);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateStatus(Status status, int taskId){

        try {
            stmt = con.createStatement();

            stmt.executeUpdate("UPDATE task SET status='"+status+"' WHERE taskId="+taskId);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTotalCount() {

        int total=0;

        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select count(*) from task");

            if(rs.next()){
                total = rs.getInt(1);
            }

            return total;

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public List<task> getPendingTasks() {

        try {
            stmt = con.createStatement();

            String status = "Done";

            //System.out.println("select * from task where status='"+status1+"' OR status='"+status2+"'");
            ResultSet rs = stmt.executeQuery("select * from task where not status='"+status+"' ORDER BY dueDate ASC");

            return createTaskList(rs);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<task> getTodaysTasks() {

        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = new Date();

            String today = dateFormat.format(date);

            stmt = con.createStatement();

           // System.out.println("select * from task where dueDate="+today);
            ResultSet rs = stmt.executeQuery("select * from task where dueDate='"+today+"'");

            return createTaskList(rs);

        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
}
