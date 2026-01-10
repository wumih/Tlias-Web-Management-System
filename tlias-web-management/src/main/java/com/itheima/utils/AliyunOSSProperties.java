package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS配置属性类
 * 用于封装阿里云对象存储服务的相关配置信息
 * 通过@ConfigurationProperties注解绑定application.yml中的aliyun.oss前缀配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    /**
     * OSS服务端点地址
     */
    private String endpoint;

    /**
     * 存储空间名称
     */
    private String bucketName;

    /**
     * 地域信息
     */
    private String region;
}
