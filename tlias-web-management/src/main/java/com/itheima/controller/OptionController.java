// com.itheima.controller.EmpController
package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/emps")
public class OptionController { 

    private final OptionService OptionService;

    /**
     * 查询全部员工；可选 job 过滤（如 ?job=1 仅查班主任）
     * 示例：/emps/list         -> 全部员工
     *      /emps/list?job=1   -> 仅班主任（用于“班主任”下拉框）
     */
    @GetMapping("/list")
    public Result list(@RequestParam(value = "job", required = false) Integer job) {
        log.info("查询员工列表, job={}", job);
        List<Emp> data = OptionService.list(job);
        return Result.success(data);
    }
}
