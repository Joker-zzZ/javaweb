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
    解决SQL注入：
        只要用户提供的信息不参与SQL语句的编译过程，问题就解决了。
        即使用户提供的信息中含有SQL语句的关键字，但是没有参与编译，不起作用。（解决SQL注入的关键：不参与编译）
        要想用户提供的信息不参与SQL语句的编译，那么必须使用 java.sql.PreparedStatement
        是属于预编译的数据库操作对象
    解决之后：
    请输入用户名：as
    请输入登录密码：as' or '1'='1
    登录失败！
 */
public class JdbcTest07 {
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");
        boolean loginResult = false;

        try {
            // 1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","333");
            // 3、获取预编译的数据库操作对象
            //sql语句的框子,一个？代表一个占位符。一个？将来接收一个值。（占位符不能使用单引号括起来）
            String sql = "select * from t_user where username = ? and userpwd = ?";
            ps = conn.prepareStatement(sql);
            //JDBC所有下标从1开始
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            // 4、执行SQL语句
            rs = ps.executeQuery();//此处不能传sql再次进行编译
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

