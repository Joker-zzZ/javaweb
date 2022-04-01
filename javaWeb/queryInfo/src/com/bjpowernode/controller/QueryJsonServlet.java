package com.bjpowernode.controller;

import com.bjpowernode.dao.ProvinceDao;
import com.bjpowernode.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class QueryJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Ĭ��ֵ��{} ��ʾjson��ʽ������
        String json = "{}";
        //��ȡ���������ʡ�ݵ�id
        String strProid = request.getParameter("proid");

        //���strProid��ֵʱ������dao��ѯ����
        if (strProid != null && strProid.trim().length() > 0) {
            ProvinceDao dao = new ProvinceDao();
            Province p = dao.queryProvinceById(Integer.valueOf(strProid));
            //ʹ��Jackson�����Province����תΪjson
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(p);
        }
        //�ѻ�ȡ�����ݣ�ͨ�����紫��ajax�е��첽������Ӧ�������
        //��ָ���������ˣ�servlet�����ظ����������json��ʽ������
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
