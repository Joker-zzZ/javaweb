package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DeptListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取应用的根路径
        String contextPath = request.getContextPath();
        //设置相应的内容类型、字符集，防止乱码
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>部门列表页面</title>");
        out.print("</head>");



        out.print("<body>");
        out.print("<script type='text/javascript'>");
        out.print("    function delete(deptno){");
        out.print("     window.confirm('asfafa');    if (window.confirm('确认删除吗？')){");
        out.print("            document.location.href = '/oa/dept/delete?deptno=' + deptno;");
        out.print("        }");
        out.print("    }");
        out.print("</script>");
        out.print("<h1>部门列表</h1>");
        out.print("<hr>");
        out.print("<form action='list.html' method='get'>");
        out.print("    <table width='40%' border='2px'>");
        out.print("        <tr>");
        out.print("            <th>序号</th>");
        out.print("            <th>部门编号</th>");
        out.print("            <th>部门名称</th>");
        out.print("            <th>操作</th>");
        out.print("        </tr>");

        //连接数据库，查询所有的部门
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DBUtil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "select deptno as dno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            //执行SQL语句
            rs = ps.executeQuery(sql);
            //处理结果集
            int i = 0;
            while (rs.next()){
                String deptno = rs.getString("dno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                out.print("        <tr>");
                out.print("            <td>"+(++i)+"</td>");
                out.print("            <td>"+deptno+"</td>");
                out.print("            <td>"+dname+"</td>");
                out.print("            <td>");
                out.print("                <a href='javascript:void(0)' onclick='delete("+deptno+")'>删除</a>");
                out.print("                <a href='edit.html'>修改</a>");
                out.print("                <a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.print("           </td>");
                out.print("        </tr>");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        out.print("    </table>");
        out.print("    <hr>");
        out.print("    <a href='add.html'>新增部门</a>");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");
    }
}
