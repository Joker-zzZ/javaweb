package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;

public interface StudentDao {
    public Student getById(String id);

    public void save(Student s);
}
