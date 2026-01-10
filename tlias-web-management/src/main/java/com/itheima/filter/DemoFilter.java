package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 演示过滤器 - 拦截所有请求进行处理
 */
@WebFilter(urlPatterns = "/*") // 拦截所有请求（演示用）
public class DemoFilter implements Filter {

    /**
     * 过滤器初始化方法
     * 在过滤器实例创建后调用，用于执行初始化操作
     *
     * @param filterConfig 过滤器配置对象，包含初始化参数等信息
     */
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("init ...");
    }

    /**
     * 执行过滤逻辑的核心方法
     * 对符合条件的请求进行拦截和处理
     *
     * @param request  Servlet请求对象
     * @param response Servlet响应对象
     * @param chain    过滤器链对象，用于调用下一个过滤器或目标资源
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("拦截到了请求...");
        // 继续执行过滤器链中的下一个元素
        chain.doFilter(request, response); // 继续后续链路
    }

    /**
     * 过滤器销毁方法
     * 在过滤器实例被销毁前调用，用于释放资源
     */
    @Override
    public void destroy() {
        System.out.println("destroy ...");
    }
}
