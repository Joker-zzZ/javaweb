package com.bjpowernode.test;

import com.bjpowernode.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        //根据id查询
        Student student = session.selectOne("test1.getById", "A0001");
        System.out.println(student);
        //session.close();
        //查询学生信息表中所有记录
        System.out.println("-------------------------------------");

        List<Student> sList = session.selectList("test1.getAll");
        for (Student s : sList) {
            System.out.println(s);
        }

        Student s1 = new Student("A0006", "cxk", 23);
        //session.insert("test1.save",s1);

        Student s2 = new Student("A0006", "cxk", 33);
        session.update("test1.update",s2);

        session.delete("test1.delete","A0006");


        session.commit();
        session.close();
    }
}
