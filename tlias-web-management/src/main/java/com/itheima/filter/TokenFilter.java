package com.itheima.filter;

import com.itheima.util.JwtUtils;           // 按你的工具类包名
import com.itheima.utils.CurrentHolder;      // 你给的 ThreadLocal 工具类
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * JWT令牌过滤器
 * 用于验证请求中的JWT令牌，解析用户信息并存储到ThreadLocal中
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    /**
     * 过滤器核心处理方法
     * 处理请求的JWT令牌验证、解析和用户信息存储
     *
     * @param req  Servlet请求对象
     * @param resp Servlet响应对象
     * @param chain 过滤器链
     * @throws IOException  IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 跨域预检请求处理
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        String uri = request.getRequestURI();
        // 白名单路径放行处理
        if (isWhitelisted(uri)) {
            chain.doFilter(request, response);
            return;
        }

        // 从请求头获取JWT令牌
        String jwt = request.getHeader("token");
        if (!StringUtils.hasText(jwt)) {
            write401(response, "缺少令牌"); // 前端会据此跳登录
            return;
        }

        try {
            // 解析JWT令牌获取声明信息
            Claims claims = JwtUtils.parseJWT(jwt);

            // 解析用户ID——兼容三种写法：userId / uid / subject(纯数字)
            Number uidNum = claims.get("userId", Number.class);
            if (uidNum == null) uidNum = claims.get("uid", Number.class);
            if (uidNum == null) {
                String sub = claims.getSubject();
                if (sub != null && sub.matches("\\d+")) uidNum = Long.valueOf(sub);
            }
            if (uidNum == null) {
                write401(response, "令牌缺少用户ID");
                return;
            }

            // 将用户ID写入请求上下文，供AOP切面读取
            CurrentHolder.setCurrentId(uidNum.intValue());

            // 请求放行
            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            log.info("令牌已过期: {}", e.getMessage());
            write401(response, "令牌已过期");
        } catch (JwtException e) {
            log.info("令牌非法: {}", e.getMessage());
            write401(response, "令牌非法");
        } catch (Exception e) {
            log.warn("令牌校验异常", e);
            write401(response, "令牌校验失败");
        } finally {
            // 清理ThreadLocal，避免线程复用导致数据串扰
            CurrentHolder.remove();
        }
    }

    /**
     * 判断URI是否在白名单中
     * 白名单路径无需令牌验证直接放行
     *
     * @param uri 请求URI
     * @return true表示在白名单中，false表示不在白名单中
     */
    private boolean isWhitelisted(String uri) {
        return uri.startsWith("/login")
                || uri.startsWith("/public")
                || uri.startsWith("/error")
                || uri.startsWith("/doc.html")
                || uri.startsWith("/swagger")
                || uri.startsWith("/v3/api-docs")
                || uri.startsWith("/webjars")
                || uri.startsWith("/favicon.ico");
    }

    /**
     * 向响应中写入401未授权错误信息
     *
     * @param response HTTP响应对象
     * @param msg 错误消息
     * @throws IOException IO异常
     */
    private void write401(HttpServletResponse response, String msg) throws IOException {
        if (response.isCommitted()) return;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"msg\":\"" + msg + "\"}");
    }
}
