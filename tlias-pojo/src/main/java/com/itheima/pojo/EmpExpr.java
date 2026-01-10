package com.itheima.pojo;
//这是一个员工工作经历的实体类，用于存储员工的工作履历信息。
//包含ID、员工ID、开始时间、结束时间、公司名称和职位等字段。
// 使用Lombok的@Data注解自动生成getter/setter方法，简化代码编写。
import lombok.Data;

import java.time.LocalDate;

/**
 * 工作经历
 */
@Data
public class EmpExpr {
    private Integer id; //ID
    private Integer empId; //员工ID
    private LocalDate begin; //开始时间
    private LocalDate end; //结束时间
    private String company; //公司名称
    private String job; //职位
}