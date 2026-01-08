// src/main/java/com/itheima/service/impl/StudentServiceImpl.java
package com.itheima.service.impl;

import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        int p = (page == null || page < 1) ? 1 : page;
        int ps = (pageSize == null || pageSize < 1) ? 10 : pageSize;
        int offset = (p - 1) * ps;

        List<Student> rows = studentMapper.list(name, degree, clazzId, offset, ps);
        Long total = studentMapper.count(name, degree, clazzId);

        return new PageResult(total, rows);
    }

    @Override
    public void create(Student student) {
        // 违纪次数/扣分数据库已设默认0，这里可不手动赋值
        // create_time / update_time 由 SQL 中 NOW() 填充
        studentMapper.insert(student);
        // 如需返回新ID，可使用 useGeneratedKeys 映射到 student.setId(...)
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.selectById(id);
    }
    @Override
    public void update(Student student) {
        int rows = studentMapper.update(student);
        if (rows == 0) {
            // 这里可选抛异常，触发你的全局异常处理，或自行返回错误
            throw new RuntimeException("学员不存在或未修改任何数据");
        }
    }

    @Override
    public void deleteByIds(String ids) {
        // 把 "1,2,3" 切分成数组
        String[] idArray = ids.split(",");
        studentMapper.deleteByIds(idArray);
    }
    @Override
    public void addViolation(Integer id, Integer score) {
        if (id == null || score == null) {
            throw new IllegalArgumentException("id 和 score 不能为空");
        }
        int rows = studentMapper.addViolation(id, score);
        if (rows == 0) {
            throw new RuntimeException("违纪处理失败，学员不存在，id=" + id);
        }
    }
}

