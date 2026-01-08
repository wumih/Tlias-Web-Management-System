// src/main/java/com/itheima/controller/StatisticsController.java
package com.itheima.controller;

import com.itheima.service.StatisticsService;
import com.itheima.pojo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    // 在 Controller 中修改返回的数据类型
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        List<Map<String, Object>> data = statisticsService.getStudentCountData();

        // 分离班级列表和人数列表
        List<String> clazzList = data.stream()
                .map(item -> (String) item.get("clazzName"))
                .toList();
        // 使用 Long 类型
        List<Long> dataList = data.stream()
                .map(item -> (Long) item.get("studentCount"))
                .toList();

        // 构建响应数据
        return Result.success(Map.of("clazzList", clazzList, "dataList", dataList));
    }
}
