package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录控制器
 * 处理员工登录相关的请求
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 员工登录接口
     * 接收员工登录信息，验证用户名和密码，生成JWT令牌并返回登录结果
     *
     * @param emp 包含用户名和密码的员工对象
     * @return Result 包含登录成功信息或错误信息的响应结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工来登录啦 , username={}", emp.getUsername()); // 避免打印明文密码

        // 调用服务层进行登录验证
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo == null) {
            return Result.error("用户名或密码错误~");
        }

        // 构建JWT令牌的载荷信息
        Map<String,Object> claims = Map.of("uid", loginInfo.getId(), "username", loginInfo.getUsername());
        // 生成JWT令牌
        String token = JwtUtils.generateJwt(claims);

        // 将生成的令牌设置到登录信息中
        loginInfo.setToken(token);
        return Result.success(loginInfo);
    }

}
