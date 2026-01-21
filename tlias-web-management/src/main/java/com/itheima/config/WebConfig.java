package com.itheima.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的路径：所有路径
        registry.addMapping("/**")
                // 允许跨域访问的源：所有源 (为了方便测试，生产环境建议指定具体域名)
                .allowedOrigins("*")
                // 允许请求方法：GET, POST, PUT, DELETE, OPTIONS 等
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许头部设置
                .allowedHeaders("*")
                // 允许携带 cookie 等认证信息 (注意：如果 allowedOrigins 是 *，这里通常不能设为 true，但在 Spring Boot 新版中配合 allowedOriginPatterns 可以，或者这里先设为 false 避免冲突，或者使用 allowedOriginPatterns("*"))
                // 为了最简单的兼容性，我们使用 allowedOriginPatterns("*") 配合 allowCredentials(true)
                // 但最稳妥的 "允许所有" 写法如下：
                .maxAge(3600);
    }
}