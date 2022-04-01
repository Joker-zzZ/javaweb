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
        //��ȡ���󴫹�����ʡ��id
        String strProvinceId = request.getParameter("proid");
        if (strProvinceId != null && !("".equals(strProvinceId.trim()))) {
            QueryDao dao = new QueryDao();
            List<City> cityList= dao.queryCityList(Integer.valueOf(strProvinceId));
            //��listתΪjson
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(cityList);
        }
        //�������
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }
}
