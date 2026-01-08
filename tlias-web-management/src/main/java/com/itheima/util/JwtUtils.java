package com.itheima.util; // 注意与你项目里的包名一致

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 至少 32 字节（示例而已！请换成你自己的随机串/配置）
    private static final String SECRET = "q2vV6nWZ4J8rLb0yS3pU9aD1kXeRgHcM7fPnYtLa"; // 40+ chars

    private static final long EXPIRE_MILLIS = 12 * 60 * 60 * 1000L; // 12小时

    private static SecretKey key() {
        // 用 hmacShaKeyFor，要求字节长度 >= 32
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /** 生成 token */
    public static String generateJwt(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXPIRE_MILLIS))
                .signWith(key(), SignatureAlgorithm.HS256) // 新 API：key + 算法
                .compact();
    }

    /** 解析/校验 token（成功返回 Claims，失败抛异常） */
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
