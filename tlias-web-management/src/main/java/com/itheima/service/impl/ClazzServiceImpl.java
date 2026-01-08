package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    private final ClazzMapper clazzMapper;

    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Override
    public PageResult page(Integer page, Integer pageSize) {
        // 1) 设置分页参数（自动帮 SQL 加 LIMIT/OFFSET）
        PageHelper.startPage(page, pageSize);

        // 2) 执行查询（此处不要手写 LIMIT）
        List<Clazz> list = clazzMapper.list();

        // 2.1) 计算班级状态
        LocalDate today = LocalDate.now();
        for (Clazz c : list) {
            if (today.isAfter(c.getEndDate())) {
                c.setStatus("已结课");
            } else if (today.isBefore(c.getBeginDate())) {
                c.setStatus("未开班");
            } else {
                c.setStatus("在读中");
            }
        }

        // 3) 解析分页结果并封装
        Page<Clazz> p = (Page<Clazz>) list;
        return new PageResult(p.getTotal(), p.getResult());
    }
    @Override
    public boolean addClazz(Clazz clazz) {
        try {
            // 调用 Mapper 层的插入方法
            int result = clazzMapper.insert(clazz);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }

    @Override
    public boolean deleteClazzById(Integer id) {
        int rowsAffected = clazzMapper.deleteClazzById(id);
        return rowsAffected > 0;  // 如果删除了班级，返回 true
    }
    @Override
    public List<Clazz> listAll() {
        return clazzMapper.listAll();
    }
}
