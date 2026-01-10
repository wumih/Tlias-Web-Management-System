package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工服务接口
 */
public interface
EmpService {
    /**
     * 分页查询员工信息
     * @param page 页码
     * @param pageSize 每页大小
     * @param name 员工姓名（可选）
     * @param gender 性别（可选）
     * @param begin 开始日期（可选）
     * @param end 结束日期（可选）
     * @return 分页查询结果
     */
    PageResult page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 保存员工信息
     * @param emp 员工对象
     */
    void save(Emp emp);

    /**
     * 添加员工
     * @param emp 员工对象
     */
    /**
     * 批量删除员工
     * @param ids 员工ID列表
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID获取员工信息
     * @param id 员工ID
     * @return 员工对象
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     * @param emp 员工对象
     */
    void update(Emp emp);

    /**
     * 员工登录
     * @param emp 员工对象（包含登录信息）
     * @return 登录信息
     */
    LoginInfo login(Emp emp);
}
