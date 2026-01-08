// src/main/java/com/itheima/service/impl/StatisticsServiceImpl.java
package com.itheima.service.impl;

import com.itheima.mapper.StatisticsMapper;
import com.itheima.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsMapper statisticsMapper;

    @Override
    public List<Map<String, Object>> getStudentCountData() {
        return statisticsMapper.getStudentCountByClazz();
    }
}
