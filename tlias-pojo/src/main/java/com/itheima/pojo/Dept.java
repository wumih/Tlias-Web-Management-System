package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 部门的实体类
 * 用于封装部门相关的信息，包括部门ID、名称以及创建和更新时间
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    /**
     * 部门唯一标识ID
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门创建时间
     */
    private LocalDateTime createTime;

    /**
     * 部门最后更新时间
     */
    private LocalDateTime updateTime;
}
