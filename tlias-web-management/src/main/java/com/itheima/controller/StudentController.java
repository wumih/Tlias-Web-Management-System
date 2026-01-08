// src/main/java/com/itheima/controller/StudentController.java
package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    // 学员列表条件分页查询
    @GetMapping
    public Result page(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer degree,
                       @RequestParam(required = false) Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult data = studentService.page(name, degree, clazzId, page, pageSize);
        return Result.success(data);
    }
    /** 添加学员 */
    @PostMapping
    public Result create(@RequestBody Student student) {
        log.info("添加学员: {}", student);
        studentService.create(student);
        return Result.success(); // 响应: {"code":1,"msg":"success","data":null}
    }
    /** 根据ID查询学员 */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学员, id={}", id);
        Student student = studentService.getById(id);
        return Result.success(student);  // 响应体符合接口文档格式
    }

    /** 修改学员 */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员：{}", student);
        studentService.update(student);
        return Result.success(); // {"code":1,"msg":"success","data":null}
    }
    /** 批量删除学员
     * 示例：DELETE /students/1,2,3
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("批量删除学员: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }
    /** 违纪处理：次数+1，扣分累计+score */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理: 学员id={}, 扣分={}", id, score);
        studentService.addViolation(id, score);
        return Result.success();
    }

}
