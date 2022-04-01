package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("	<meta charset='UTF-8'>");
        out.print("	<title>����ҳ��</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("	<h1>��������</h1>");
        out.print("	<hr>");

        //��ȡ���ű��
        String deptno = request.getParameter("deptno");
        //�������ݿ⣬���ݲ��ű�Ų�ѯ������Ϣ
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();//����������ֻ��һ����¼��������
            if (rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("        ���ű�ţ�"+deptno+" <br>");
                out.print("        �������ƣ�"+dname+" <br>");
                out.print("        ����λ�ã�"+loc+" <br>");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        out.print("	<input type='button' value='����' onclick='window.history.back()'>");
        out.print("</body>");
        out.print("</html>");
    }
}