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
        //处理get请求
        //System.out.println("相应了ajax请求！！");

        String strProid = request.getParameter("proid");
        System.out.println("strProid= " + strProid);
        String name = "默认无数据";

        //访问dao，查询数据库
        if (strProid != null && !("".equals(strProid.trim()))) {
            //创建dao对象，调用方法
            ProvinceDao dao = new ProvinceDao();
            name = dao.queryProvinceNameById(Integer.valueOf(strProid));
        }

        //使用HttpServletResponse输出数据
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //out.print("中国！");
        out.print(name);
        out.flush();
        out.close();
    }
}
