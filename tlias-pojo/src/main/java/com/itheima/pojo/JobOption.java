package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 职位选项实体类
 * 用于封装职位相关的数据列表信息
 * 方便Echarts绘制饼状图
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {
    /**
     * 职位列表
     * 存储职位相关信息的列表
     */
    private List jobList;

    /**
     * 数据列表
     * 存储其他相关数据的列表
     */
    private List dataList;
}