package com.itheima.pojo;

import com.itheima.pojo.Clazz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClazzOption {
    private int total;
    private List<Clazz> rows;
}
