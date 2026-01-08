// src/main/java/com/itheima/service/StatisticsService.java
package com.itheima.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    // 获取每个班级的人数
    List<Map<String, Object>> getStudentCountData();
}
