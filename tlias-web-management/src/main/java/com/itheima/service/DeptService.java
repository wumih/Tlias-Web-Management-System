package com.itheima.service;
//这是一个部门服务接口，
// 定义了部门管理的基本操作：
// 查询所有部门、根据ID删除部门、新增部门、根据ID查询部门、更新部门信息。
import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     */
    public List<Dept> findAll();
    /**
     * 根据id删除部门
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    void save(Dept dept);

    /**
     * 根据id查询部门
     */
    Dept getById(Integer id);

    void update(Dept dept);


}