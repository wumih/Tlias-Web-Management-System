package com.itheima.mapper;

import com.itheima.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工日志数据访问层接口
 */
@Mapper
public interface EmpLogMapper {

    /**
     * 插入员工操作日志记录
     *
     * @param empLog 员工日志对象，包含操作时间和日志信息
     */
    @Insert("insert into emp_log (operate_time, info) values (#{operateTime}, #{info})")
    public void insert(EmpLog empLog);
}
