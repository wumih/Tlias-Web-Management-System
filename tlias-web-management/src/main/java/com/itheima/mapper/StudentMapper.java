// src/main/java/com/itheima/mapper/StudentMapper.java
package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    // 条件分页列表
    List<Student> list(@Param("name") String name,
                       @Param("degree") Integer degree,
                       @Param("clazzId") Integer clazzId,
                       @Param("offset") Integer offset,
                       @Param("pageSize") Integer pageSize);

    // 统计总数
    Long count(@Param("name") String name,
               @Param("degree") Integer degree,
               @Param("clazzId") Integer clazzId);
    int insert(Student student);
    Student selectById(Integer id);

    int update(Student student);

    void deleteByIds(@Param("ids") String[] ids);
    int addViolation(@Param("id") Integer id, @Param("score") Integer score);

    /** 统计学员学历信息（返回 name / value） */
    List<Map<String, Object>> countStudentDegreeData();
}


