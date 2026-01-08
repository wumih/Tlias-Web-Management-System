package com.itheima;

import io.jsonwebtoken.*;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 注意：这里用的是“明文秘钥字符串”，签发与解析必须完全一致
    private static final String SECRET = "itcast-very-secret-123456"; // 不要用太短/太简单
    private static final long   EXPIRE_MS = 12 * 60 * 60 * 1000L;     // 12 小时

    /** 生成 JWT */
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);
        claims.put("username", "itheima");

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET) // 0.9.1 的写法
                .compact();

        System.out.println("JWT: " + jwt);
    }

    /** 解析 JWT（这里先现签一个，再解析，避免过期问题） */
    @Test
    public void testParseJwt() {
        String jwt = Jwts.builder()
                .claim("id", 10)
                .claim("username", "itheima")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 分钟
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(jwt)
                    .getBody();

            System.out.println("id=" + claims.get("id"));
            System.out.println("username=" + claims.get("username"));
            System.out.println("exp=" + claims.getExpiration());
        } catch (ExpiredJwtException e) {
            System.out.println("Token已过期：" + e.getClaims());
        } catch (SignatureException e) {
            System.out.println("签名不匹配（秘钥不一致或被篡改）");
        } catch (JwtException e) {
            System.out.println("无效Token：" + e.getMessage());
        }
    }
}
