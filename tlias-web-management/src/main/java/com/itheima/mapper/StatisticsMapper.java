// src/main/java/com/itheima/mapper/StatisticsMapper.java
package com.itheima.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticsMapper {

    // 查询每个班级的名称和人数
    @Select("""
        SELECT c.name AS clazzName, COUNT(s.id) AS studentCount
        FROM clazz c
        LEFT JOIN student s ON c.id = s.clazz_id
        GROUP BY c.name
    """)
    List<Map<String, Object>> getStudentCountByClazz();
}
