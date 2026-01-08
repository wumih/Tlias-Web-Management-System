package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    @Select("""
    select 
      c.id,
      c.name,
      c.room,
      c.begin_date  as beginDate,
      c.end_date    as endDate,
      c.master_id   as masterId,
      c.subject,
      c.create_time as createTime,
      c.update_time as updateTime,
      e.name        as masterName
    from clazz c
    left join emp e on c.master_id = e.id
    """)
    List<Clazz> list();
    // 插入班级
    int insert(Clazz clazz);
    // 根据ID查询班级
    Clazz getById(Integer id);
    // 根据ID修改班级
    int update(Clazz clazz);
    // 删除班级
    @Delete("DELETE FROM clazz WHERE id = #{id}")
    int deleteClazzById(Integer id);

    List<Clazz> listAll();
}
