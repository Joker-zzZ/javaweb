package com.bjpowernode.oa;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    //注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return conn 连接对象
     * @throws SQLException
     */
    //获取连接
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }
    //获取数据库操作对象
    //执行SQL
    //处理查询结果集
    //释放资源

    /**
     * 释放资源
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 结果集对象
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
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

}
