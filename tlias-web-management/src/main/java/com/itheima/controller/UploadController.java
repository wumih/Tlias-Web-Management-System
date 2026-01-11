
package com.itheima.controller;

import com.itheima.pojo.Result;
import com.aliyun.oss.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

/**
 * 文件上传控制器
 * 处理文件上传请求，将文件上传到阿里云OSS并返回访问URL
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 上传文件接口
     * 接收前端上传的文件，生成唯一文件名后上传到阿里云OSS
     *
     * @param file 上传的文件对象
     * @return Result 包含上传结果的响应对象，成功时返回文件访问URL，失败时返回错误信息
     * @throws Exception 文件上传过程中可能抛出的异常
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}", file);
        if (!file.isEmpty()) {
            // 获取原始文件名和扩展名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 生成唯一文件名
            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
            // 上传文件到阿里云OSS
            String url = aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);
            return Result.success(url);
        }
        return Result.error("上传失败");
    }

}