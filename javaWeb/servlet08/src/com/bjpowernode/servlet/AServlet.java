package com.bjpowernode.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date nowDate = new Date();
        request.setAttribute("nowDate",nowDate);
        Object nowDate1 = request.getAttribute("nowDate");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(request.getAttribute("nowDate1"));
    }
}
