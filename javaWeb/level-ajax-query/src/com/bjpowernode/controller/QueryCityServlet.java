package com.bjpowernode.controller;

import com.bjpowernode.dao.QueryDao;
import com.bjpowernode.entity.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        //获取请求传过来的省份id
        String strProvinceId = request.getParameter("proid");
        if (strProvinceId != null && !("".equals(strProvinceId.trim()))) {
            QueryDao dao = new QueryDao();
            List<City> cityList= dao.queryCityList(Integer.valueOf(strProvinceId));
            //把list转为json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(cityList);
        }
        //输出数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }
}
