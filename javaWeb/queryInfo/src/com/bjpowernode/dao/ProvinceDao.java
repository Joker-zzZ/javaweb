package com.bjpowernode.dao;

import com.bjpowernode.entity.Province;

import java.sql.*;

public class ProvinceDao {
    //根据id获取名称
    public String queryProvinceNameById(Integer provinceId) {
        String url = "jdbc:mysql://localhost:3306/springdb";
        String username = "root";
        String password = "33333";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        String name = "";

        //注册驱动
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
        //获取连接
        //创建数据库连接对象
        //执行SQL
        //处理查询结果集
        //释放资源
        return name;
    }
    //根据id获取完整Province对象
    public Province queryProvinceById(Integer provinceId) {
        String url = "jdbc:mysql://localhost:3306/springdb";
        String username = "root";
        String password = "33333";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        Province province = null;

        //注册驱动
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
        //获取连接
        //创建数据库连接对象
        //执行SQL
        //处理查询结果集
        //释放资源
        return province;
    }
}
