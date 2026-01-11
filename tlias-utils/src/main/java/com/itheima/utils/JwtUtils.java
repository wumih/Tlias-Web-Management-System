package com.itheima.utils; // 注意与你项目里的包名一致

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类，用于生成和解析JWT令牌
 */
@Component
public class JwtUtils {

    // 从配置文件读取JWT密钥
    @Value("${app.jwt.secret}")
    private String secret;
    
    // 从配置文件读取JWT过期时间
    @Value("${app.jwt.expire-millis}")
    private long expireMillis;
    
    // 静态实例，用于保持静态方法的调用方式
    private static JwtUtils instance;
    
    // Spring初始化时设置实例
    public JwtUtils() {
        instance = this;
    }
    
    // 获取JWT密钥
    private static String getSecret() {
        return instance.secret;
    }
    
    // 获取JWT过期时间
    private static long getExpireMillis() {
        return instance.expireMillis;
    }

    /**
     * 获取JWT签名密钥
     * @return SecretKey类型的签名密钥
     */
    private static SecretKey key() {
        // 用 hmacShaKeyFor，要求字节长度 >= 32
        return Keys.hmacShaKeyFor(getSecret().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成JWT令牌
     * @param claims JWT中包含的声明信息
     * @return 生成的JWT字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + getExpireMillis()))
                .signWith(key(), SignatureAlgorithm.HS256) // 新 API：key + 算法
                .compact();
    }

    /**
     * 解析并校验JWT令牌
     * @param jwt 待解析的JWT字符串
     * @return 解析成功的Claims对象
     * @throws Exception 解析失败时抛出异常
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
