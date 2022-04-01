package com.bjpowernode.controller;

import com.bjpowernode.dao.ProvinceDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/queryProvince"})
public class QueryProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //����get����
        //System.out.println("��Ӧ��ajax���󣡣�");

        String strProid = request.getParameter("proid");
        System.out.println("strProid= " + strProid);
        String name = "Ĭ��������";

        //����dao����ѯ���ݿ�
        if (strProid != null && !("".equals(strProid.trim()))) {
            //����dao���󣬵��÷���
            ProvinceDao dao = new ProvinceDao();
            name = dao.queryProvinceNameById(Integer.valueOf(strProid));
        }

        //ʹ��HttpServletResponse�������
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //out.print("�й���");
        out.print(name);
        out.flush();
        out.close();
    }
}
