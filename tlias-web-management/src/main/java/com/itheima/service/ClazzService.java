package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult page(Integer page, Integer pageSize);
    boolean addClazz(Clazz clazz);

    Clazz getById(Integer id);

    int update(Clazz clazz);
    // 删除班级
    boolean deleteClazzById(Integer id);
    List<Clazz> listAll();

}
