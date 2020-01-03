package com.dileep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet1 extends HttpServlet {
    public Servlet1() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        int taskId = Integer.parseInt(req.getParameter("id"));
        TaskManager taskManager = new TaskManager();
        Task taskObj = taskManager.searchByTaskId(taskId);
        PrintWriter printWriter = resp.getWriter();
        if (taskObj == null) resp.setStatus(404);
        else {
            printWriter.println(taskObj);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        int taskId = Integer.parseInt(req.getParameter("id"));
        TaskManager taskManager = new TaskManager();
        if (!taskManager.delete(taskId)) {
            resp.setStatus(404);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        int taskId = Integer.parseInt(req.getParameter("id"));
        TaskManager taskManager = new TaskManager();
        taskManager.updateStatus(Status.valueOf(status), taskId);
    }
}
