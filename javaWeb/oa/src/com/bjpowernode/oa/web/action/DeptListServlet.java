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
        //��ȡӦ�õĸ�·��
        String contextPath = request.getContextPath();
        //������Ӧ���������͡��ַ�������ֹ����
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>�����б�ҳ��</title>");
        out.print("</head>");



        out.print("<body>");
        out.print("<script type='text/javascript'>");
        out.print("    function delete(deptno){");
        out.print("     window.confirm('asfafa');    if (window.confirm('ȷ��ɾ����')){");
        out.print("            document.location.href = '/oa/dept/delete?deptno=' + deptno;");
        out.print("        }");
        out.print("    }");
        out.print("</script>");
        out.print("<h1>�����б�</h1>");
        out.print("<hr>");
        out.print("<form action='list.html' method='get'>");
        out.print("    <table width='40%' border='2px'>");
        out.print("        <tr>");
        out.print("            <th>���</th>");
        out.print("            <th>���ű��</th>");
        out.print("            <th>��������</th>");
        out.print("            <th>����</th>");
        out.print("        </tr>");

        //�������ݿ⣬��ѯ���еĲ���
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //��ȡ����
            conn = DBUtil.getConnection();
            //��ȡԤ��������ݿ��������
            String sql = "select deptno as dno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            //ִ��SQL���
            rs = ps.executeQuery(sql);
            //��������
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
                out.print("                <a href='javascript:void(0)' onclick='delete("+deptno+")'>ɾ��</a>");
                out.print("                <a href='edit.html'>�޸�</a>");
                out.print("                <a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>����</a>");
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
        out.print("    <a href='add.html'>��������</a>");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");
    }
}
