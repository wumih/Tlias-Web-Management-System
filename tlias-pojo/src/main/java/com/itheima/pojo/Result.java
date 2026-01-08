package com.itheima.pojo;

import lombok.Data;

/**
 * 后端统一返回结果
 * 用于封装API接口的统一返回格式，包含状态码、消息和数据
 */
@Data
public class Result {

    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private Object data; //数据

    /**
     * 创建成功的返回结果
     * @return Result 成功的返回结果对象
     */
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    /**
     * 创建包含数据的成功返回结果
     * @param object 返回的数据对象
     * @return Result 包含数据的成功返回结果对象
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    /**
     * 创建失败的返回结果
     * @param msg 错误信息
     * @return Result 失败的返回结果对象
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
