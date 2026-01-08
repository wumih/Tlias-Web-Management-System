package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Option;
import java.util.List;

public interface OptionService { 
    /** 班主任/员工 选项列表；job 为空则返回全部员工 */
    List<Emp> list(Integer job);
}
