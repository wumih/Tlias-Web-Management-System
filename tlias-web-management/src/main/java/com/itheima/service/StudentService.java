// src/main/java/com/itheima/service/StudentService.java
package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;

public interface StudentService {
    PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);
    void create(Student student);
    Student getById(Integer id);
    void update(Student student);

    void deleteByIds(String ids);

    void addViolation(Integer id, Integer score);
}
