package com.bjpowernode.service;

import com.bjpowernode.domain.Student;

public interface StudentService {

    public Student getById(String id);

    public void save(Student s);
}
