package com.dileep;

import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Servlets extends HttpServlet {
    public Servlets() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        TaskManager taskManager = new TaskManager();
        List<task> taskList = taskManager.display();

        JSONArray gh = new JSONArray(taskList);
        PrintWriter out = resp.getWriter();

        out.println(gh);
    }
}
