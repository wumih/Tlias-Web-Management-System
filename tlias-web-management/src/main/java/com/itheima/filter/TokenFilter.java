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

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 1) 跨域预检直接放行（如是同域可保留也无害）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        String uri = request.getRequestURI();
        // 2) 白名单放行：登录、静态资源、错误页、swagger 等按需补充
        if (isWhitelisted(uri)) {
            chain.doFilter(request, response);
            return;
        }

        // 3) 只从 `token` 头取 JWT（你要求的格式）
        String jwt = request.getHeader("token");
        if (!StringUtils.hasText(jwt)) {
            write401(response, "缺少令牌"); // 前端会据此跳登录
            return;
        }

        try {
            // 4) 解析 JWT（方法名按你的 JwtUtils 实际为 parseJWT/parseToken）
            Claims claims = JwtUtils.parseJWT(jwt);

            // 5) 解析用户ID——兼容三种写法：userId / uid / subject(纯数字)
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

            // 6) 写入请求上下文，供 AOP 切面读取
            CurrentHolder.setCurrentId(uidNum.intValue());

            // 放行
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
            // 7) 必须清理，避免线程复用导致“串号”
            CurrentHolder.remove();
        }
    }

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

    private void write401(HttpServletResponse response, String msg) throws IOException {
        if (response.isCommitted()) return;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"msg\":\"" + msg + "\"}");
    }
}
