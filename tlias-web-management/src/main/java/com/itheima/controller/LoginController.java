package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工来登录啦 , username={}", emp.getUsername()); // 避免打印明文密码

        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo == null) {
            return Result.error("用户名或密码错误~");
        }

        // 使用和过滤器同一个 JwtUtils 来签发
        Map<String,Object> claims = Map.of("uid", loginInfo.getId(), "username", loginInfo.getUsername());
        String token = JwtUtils.generateJwt(claims);

        // 确保 LoginInfo 有 token 字段，返回给前端
        loginInfo.setToken(token);
        return Result.success(loginInfo);
    }

}