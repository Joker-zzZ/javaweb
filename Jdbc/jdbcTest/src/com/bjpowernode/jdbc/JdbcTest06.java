package com.bjpowernode.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
    需求：模拟用户登录功能的实现
    业务描述：程序运行的时候，提供一个用户输入的入口来接收用户输入的用户名和密码，
        用户输入用户名和密码之后，提交信息，java程序接收到用户输入的信息，java程序连接数据库验证用户名和密码是否正确
        合法显示登陆成功，否则显示登陆失败。
    Bug(sql注入):
    请输入用户名：asd
    请输入登录密码：asd' or '1'='1
    登录成功！
 */
public class JdbcTest06 {
    public static void main(String[] args) {
        //初始化界面
        Map<String,String> userLoginInfo = initUI();
        //验证登录结果
        boolean loginResult = login(userLoginInfo);
        //输入验证结果
        System.out.println(loginResult ? "登录成功！" : "登录失败！");

    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return 登录结果true表示登录成功，false表示登录失败
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        //JDBC代码
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");
        boolean loginResult = false;

        try {
            // 1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","333");
            // 3、获取数据库操作对象
            stmt = conn.createStatement();
            // 4、执行SQL语句
            String sql = "select * from t_user where username = '"+loginName+"' and userpwd = '"+loginPwd+"'";
            rs = stmt.executeQuery(sql);
            // 5、处理查询结果集
            if (rs.next()){
                loginResult = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
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
        return loginResult;
    }

    /**
     * 初始化登录界面
     * @return 用户输入的用户名、密码和其他信息
     */
    private static Map<String,String> initUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String loginName = scanner.nextLine();
        System.out.print("请输入登录密码：");
        String loginPwd = scanner.nextLine();
        Map<String,String> userlogimInfo = new HashMap<>();
        userlogimInfo.put("loginName",loginName);
        userlogimInfo.put("loginPwd",loginPwd);
        return userlogimInfo;
    }
}
