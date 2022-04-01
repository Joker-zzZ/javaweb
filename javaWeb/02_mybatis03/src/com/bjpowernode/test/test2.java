package com.bjpowernode.test;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class test2 {
    public static void main(String[] args) {
        StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);
        /*Student s1 = studentDao.select1("A0002");
        System.out.println(s1);

        List<Student> sList = studentDao.select2(23);
        for (Student s : sList) {
            System.out.println(s);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", "wyf");
        map.put("age", 23);
        List<Student> sList2 = studentDao.select3(map);
        for (Student s : sList2) {
            System.out.println(s);
        }
        System.out.println("-------------------------------------");

        List<Student> sList3 = studentDao.select4("z");
        for (Student s : sList3) {
            System.out.println(s);
        }
        System.out.println("----------------------------------------");

        List<Map<String, Object>> mapList = studentDao.select5();
        for (Map<String, Object> map2 : mapList) {
            Set<String> keySet = map2.keySet();
            for (String key : keySet) {
                System.out.println(key + "==" + map2.get(key));
            }
            System.out.println("--------------");
        }
        System.out.println("----------------------------------------------");*/

        /*List<Student> sList4 = studentDao.select6();
        for (Student s : sList4) {
            System.out.println(s);
        }*/

    }
}
