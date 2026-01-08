package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 通用下拉选项结构：value=主键id, label=显示名称 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private Integer value;
    private String label;
}
