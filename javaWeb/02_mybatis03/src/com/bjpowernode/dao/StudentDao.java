package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;
import com.bjpowernode.vo.StudentAndClassroomVo;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public Student getById(String id);

    public void save(Student s);

    List<Student> getAll();

    Student select1(String s);

    List<Student> select2(int i);

    List<Student> select3(Map<String, Object> map);

    List<Student> select4(String z);

    List<Map<String, Object>> select5();

    List<Student> select6();

    List<Student> select7(Student s1);

    List<Student> select8(String[] strArr);

    List<Map<String,Object>> select9();

    List<StudentAndClassroomVo> select10();

    List<StudentAndClassroomVo> select11(String z);
}
