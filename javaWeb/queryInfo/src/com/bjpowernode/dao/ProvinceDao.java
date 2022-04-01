package com.bjpowernode.dao;

import com.bjpowernode.entity.Province;

import java.sql.*;

public class ProvinceDao {
    //����id��ȡ����
    public String queryProvinceNameById(Integer provinceId) {
        String url = "jdbc:mysql://localhost:3306/springdb";
        String username = "root";
        String password = "33333";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        String name = "";

        //ע������
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            sql = "select name from province where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, provinceId);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //��ȡ����
        //�������ݿ����Ӷ���
        //ִ��SQL
        //�����ѯ�����
        //�ͷ���Դ
        return name;
    }
    //����id��ȡ����Province����
    public Province queryProvinceById(Integer provinceId) {
        String url = "jdbc:mysql://localhost:3306/springdb";
        String username = "root";
        String password = "33333";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        Province province = null;

        //ע������
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            sql = "select id,name,jiancheng,shenghui from province where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, provinceId);
            rs = ps.executeQuery();
            if (rs.next()) {
                province = new Province();
                province.setId(rs.getInt("id"));
                province.setName(rs.getString("name"));
                province.setJiancheng(rs.getString("jiancheng"));
                province.setShenghui(rs.getString("shenghui"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //��ȡ����
        //�������ݿ����Ӷ���
        //ִ��SQL
        //�����ѯ�����
        //�ͷ���Դ
        return province;
    }
}
