package com.bjpowernode.test;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.SqlSessionUtil;
import com.bjpowernode.vo.StudentAndClassroomVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class test3 {
    public static void main(String[] args) {
        StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);
        //动态Sql where标签+if标签
        Student s1 = new Student();
        s1.setName("y");
        s1.setAddress("g");
        List<Student> sList = studentDao.select7(s1);
        for (Student s : sList) {
            System.out.println(s);
        }
        System.out.println("--------------------------------------------------------");

        //动态标签 foreach标签(相当于 in(,,,) )
        String strArr[] = {"A0001", "A0002", "A0003"};
        List<Student> sList2 = studentDao.select8(strArr);
        for (Student s : sList2) {
            System.out.println(s);
        }
        System.out.println("--------------------------------------------------------");

        //多表联查 查询学生姓名和班级名称
        List<Map<String, Object>> mapList = studentDao.select9();
        for (Map<String, Object> map : mapList) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                System.out.println(key + "====" + map.get(key));
            }
            System.out.println("-----------------------------------------------------");
        }

        //多表联查 查询所有学生和班级信息
        List<StudentAndClassroomVo> voList = studentDao.select10();
        for (StudentAndClassroomVo vo : voList) {
            System.out.println(vo);
        }
        System.out.println("----------------------------------------------------");

        //多表联查 查询出学生姓名含有“z”的所有学生和班级信息
        List<StudentAndClassroomVo> voList2 = studentDao.select11("z");
        for (StudentAndClassroomVo vo : voList2) {
            System.out.println(vo);
        }
        System.out.println("----------------------------------------------------");
    }
}
