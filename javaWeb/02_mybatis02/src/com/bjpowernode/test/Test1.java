package com.bjpowernode.test;

import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import com.bjpowernode.service.impl.StudentServiceImpl;
import com.bjpowernode.util.ServiceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());
        Student s1 = new Student("A0006", "cxk", 23);
        ss.save(s1);
        Student s2 = ss.getById("A0002");
        System.out.println(s2);
    }
}
