package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpLog;
import com.itheima.pojo.LoginInfo;

/**
 * 员工日志服务接口
 * 提供员工操作日志的记录功能
 */
public interface EmpLogService {
    /**
     * 记录新增员工日志
     * @param empLog 员工日志对象，包含日志相关信息
     */
    public void insertLog(EmpLog empLog);



}
