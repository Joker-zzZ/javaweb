package com.bjpowernode.dao;

import com.bjpowernode.entity.City;
import com.bjpowernode.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    private String url = "jdbc:mysql://localhost:3306/springdb";
    private String username = "root";
    private String password = "33333";

    //查询所有的省份信息
    public List<Province> queryProvinceList() {
        List<Province> provinces = new ArrayList<>();
        Province p = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            sql = "select id,name,jiancheng,shenghui from province order by id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Province(rs.getInt("id"), rs.getString("name"), rs.getString("jiancheng"), rs.getString("shenghui"));
                provinces.add(p);
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
        return provinces;
    }

    //查询省份对应的城市
    public List<City> queryCityList(Integer provinceId) {
        List<City> cities = new ArrayList<>();
        City city = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            sql = "select id,name from city where provinceid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, provinceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                city = new City(rs.getInt("id"), rs.getString("name"));
                cities.add(city);
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
        return cities;
    }
}
