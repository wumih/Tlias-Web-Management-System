package com.itheima.pojo;
//用于封装分页查询结果。total字段存储总记录数，rows字段存储当前页的数据列表。
//用Lombok注解自动生成getter/setter、无参构造和全参构造方法。
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private Long total; //总记录数
    private List rows; //当前页数据列表
}