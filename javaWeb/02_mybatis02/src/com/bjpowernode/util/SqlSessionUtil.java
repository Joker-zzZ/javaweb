package com.bjpowernode.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    private SqlSessionUtil(){}

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    public static ThreadLocal<SqlSession> t = new ThreadLocal<SqlSession>();

    //获取SqlSession对象
    public static SqlSession getSession(){
        SqlSession session = t.get();
        if (session == null){
            session = sqlSessionFactory.openSession();
            t.set(session);//必须写！
        }
        return session;
    }
    //关闭SqlSession对象
    public static void myClose(SqlSession session){
        session.close();
        t.remove();//这句必须写！！！
    }
}
