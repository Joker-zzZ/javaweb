package com.bjpowernode.servlet;

import com.bjpowernode.domain.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyServlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student("A1001","zs",22);
        //{"id":"?","name":"?","age":?}
        String strJson = "{\"id\":\""+student.getId()+"\",\"name\":\""+student.getName()+"\",\"age\":"+student.getAge()+"}";

        response.getWriter().println(strJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
