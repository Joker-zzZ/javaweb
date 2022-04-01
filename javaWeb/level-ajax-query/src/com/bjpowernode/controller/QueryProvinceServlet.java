package com.bjpowernode.controller;

import com.bjpowernode.dao.QueryDao;
import com.bjpowernode.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        //调用dao，获取所有的省份集合List
        QueryDao dao = new QueryDao();
        List<Province> provinces = dao.queryProvinceList();
        //把List转为json格式的数据，输出给ajax请求
        if (provinces != null) {
            //调用Jackson工具库，实现List-->json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(provinces);
        }
        //输出json数据，响应ajax请求，返回数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
