package com.itheima.service.impl;
//这段代码是部门服务的实现类，实现了DeptService接口。通过@Autowired注入DeptMapper进行数据库操作，
//提供了部门的增删改查功能。其中保存和更新方法会自动设置创建时间和更新时间，
//删除方法根据ID删除部门，查询方法支持查找所有部门和根据ID获取单个部门。

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询所有部门信息
     * @return 部门列表
     */
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 根据ID删除部门
     * @param id 要删除的部门ID
     */
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    /**
     * 新增保存部门信息
     * @param dept 要保存的部门对象
     */
    public void save(Dept dept) {
        //补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //保存部门
        deptMapper.insert(dept);
    }

    /**
     * 根据ID获取部门信息
     * @param id 部门ID
     * @return 部门对象
     */
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    /**
     * 更新部门信息
     * @param dept 要更新的部门对象
     */
    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        //保存部门
        deptMapper.update(dept);
    }
}
