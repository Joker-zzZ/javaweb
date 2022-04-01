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
        //默认值：{} 表示json格式的数据
        String json = "{}";
        //获取请求参数，省份的id
        String strProid = request.getParameter("proid");

        //如果strProid有值时，调用dao查询数据
        if (strProid != null && strProid.trim().length() > 0) {
            ProvinceDao dao = new ProvinceDao();
            Province p = dao.queryProvinceById(Integer.valueOf(strProid));
            //使用Jackson对象把Province对象转为json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(p);
        }
        //把获取的数据，通过网络传给ajax中的异步对象，响应结果数据
        //把指定服务器端（servlet）返回给浏览器的是json格式的数据
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
