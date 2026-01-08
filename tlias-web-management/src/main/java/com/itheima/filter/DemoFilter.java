package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*") // 拦截所有请求（演示用）
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("init ...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("拦截到了请求...");
        chain.doFilter(request, response); // 继续后续链路
    }

    @Override
    public void destroy() {
        System.out.println("destroy ...");
    }
}
