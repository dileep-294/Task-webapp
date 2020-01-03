package com.dileep;

import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Servlets extends HttpServlet {
    public Servlets() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        TaskManager taskManager = new TaskManager();
        List<Task> taskList = taskManager.display();

        JSONArray gh = new JSONArray(taskList);
        PrintWriter out = resp.getWriter();

        out.println(gh);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        TaskManager taskManager = new TaskManager();
        //int taskId = 1+new Random().nextInt(1000);
        String Name = req.getParameter("taskName");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        String date = req.getParameter("dueDate");

        //task taskObj = new task();
//        taskObj.setTaskId(taskId);
//        taskObj.setName(Name);
//        taskObj.setStatus(Status.valueOf(status));
//        taskObj.setDescription(description);
//        try {
//            taskObj.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        try {
            taskManager.addTask(Name, description, Status.valueOf(status), new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(201);
    }
}
