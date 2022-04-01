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
        //����dao����ȡ���е�ʡ�ݼ���List
        QueryDao dao = new QueryDao();
        List<Province> provinces = dao.queryProvinceList();
        //��ListתΪjson��ʽ�����ݣ������ajax����
        if (provinces != null) {
            //����Jackson���߿⣬ʵ��List-->json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(provinces);
        }
        //���json���ݣ���Ӧajax���󣬷�������
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
