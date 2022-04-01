package com.bjpowernode.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
    ����ģ���û���¼���ܵ�ʵ��
    ҵ���������������е�ʱ���ṩһ���û����������������û�������û��������룬
        �û������û���������֮���ύ��Ϣ��java������յ��û��������Ϣ��java�����������ݿ���֤�û����������Ƿ���ȷ
        �Ϸ���ʾ��½�ɹ���������ʾ��½ʧ�ܡ�
    Bug(sqlע��):
    �������û�����asd
    �������¼���룺asd' or '1'='1
    ��¼�ɹ���
    ���SQLע�룺
        ֻҪ�û��ṩ����Ϣ������SQL���ı�����̣�����ͽ���ˡ�
        ��ʹ�û��ṩ����Ϣ�к���SQL���Ĺؼ��֣�����û�в�����룬�������á������SQLע��Ĺؼ�����������룩
        Ҫ���û��ṩ����Ϣ������SQL���ı��룬��ô����ʹ�� java.sql.PreparedStatement
        ������Ԥ��������ݿ��������
    ���֮��
    �������û�����as
    �������¼���룺as' or '1'='1
    ��¼ʧ�ܣ�
 */
public class JdbcTest07 {
    public static void main(String[] args) {
        //��ʼ������
        Map<String,String> userLoginInfo = initUI();
        //��֤��¼���
        boolean loginResult = login(userLoginInfo);
        //������֤���
        System.out.println(loginResult ? "��¼�ɹ���" : "��¼ʧ�ܣ�");

    }

    /**
     * �û���¼
     * @param userLoginInfo �û���¼��Ϣ
     * @return ��¼���true��ʾ��¼�ɹ���false��ʾ��¼ʧ��
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        //JDBC����
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");
        boolean loginResult = false;

        try {
            // 1��ע������
            Class.forName("com.mysql.jdbc.Driver");
            // 2����ȡ����
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","333");
            // 3����ȡԤ��������ݿ��������
            //sql���Ŀ���,һ��������һ��ռλ����һ������������һ��ֵ����ռλ������ʹ�õ�������������
            String sql = "select * from t_user where username = ? and userpwd = ?";
            ps = conn.prepareStatement(sql);
            //JDBC�����±��1��ʼ
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            // 4��ִ��SQL���
            rs = ps.executeQuery();//�˴����ܴ�sql�ٴν��б���
            // 5�������ѯ�����
            if (rs.next()){
                loginResult = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6���ͷ���Դ
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
     * ��ʼ����¼����
     * @return �û�������û����������������Ϣ
     */
    private static Map<String,String> initUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("�������û�����");
        String loginName = scanner.nextLine();
        System.out.print("�������¼���룺");
        String loginPwd = scanner.nextLine();
        Map<String,String> userlogimInfo = new HashMap<>();
        userlogimInfo.put("loginName",loginName);
        userlogimInfo.put("loginPwd",loginPwd);
        return userlogimInfo;
    }
}

