package com.itheima.service.impl;

import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpLog;
import com.itheima.pojo.LoginInfo;
import com.itheima.service.EmpLogService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;                 // ★ 引入日志
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j                                             // ★ 开启日志
@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Autowired                                      // ★ 别忘了注入，否则为 null
    private EmpMapper empMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }


}
