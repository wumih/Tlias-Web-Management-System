package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Option;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import com.itheima.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/clazzs")
public class ClazzController {

    // 使用构造器注入，final 字段 + @RequiredArgsConstructor 即可，不要再加 @Autowired
    private final ClazzService clazzService;
    private final OptionService optionService;

    // 分页查询：/clazzs?page=1&pageSize=10
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询班级列表 page={}, pageSize={}", page, pageSize);
        PageResult data = clazzService.page(page, pageSize);
        return Result.success(data);
    }

    // 添加班级
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("添加班级: {}", clazz);

        boolean success = clazzService.addClazz(clazz);
        if (success) {
            return Result.success();
        } else {
            return Result.error("添加班级失败");
        }
    }


    // 根据ID查询班级
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询班级，id={}", id);
        Clazz clazz = clazzService.getById(id);
        if (clazz != null) {
            return Result.success(clazz);
        } else {
            return Result.error("班级不存在");
        }
    }

    // 修改班级
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级：{}", clazz);
        int rows = clazzService.update(clazz);
        if (rows > 0) {
            return Result.success();
        } else {
            return Result.error("修改失败，班级不存在");
        }
    }

    /**
     * 删除班级
     * @param id 班级ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        log.info("删除班级, id={}", id);
        boolean success = clazzService.deleteClazzById(id);
        if (success) {
            return Result.success(null);
        } else {
            return Result.error("删除失败");
        }
    }
    /** 查询所有班级 */
    @GetMapping("/list")
    public Result listAll() {
        log.info("查询所有班级");
        List<Clazz> data = clazzService.listAll();
        return Result.success(data);
    }

}
