package com.joyin.simplemail.pojo;

import lombok.Data;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/10/11 13:25
 */
@Data
// if we didn't gen the setter, when we send request,
// got a error like
// 'No converter found for return value of type: class com.imooc.common.JsonResult]'
public class JsonResult<T> {

    private int status;

    private String msg;

    private T data;

    public JsonResult(T t) {
        this.status = 200;
        this.msg = "ok";
        this.data = t;
    }

    public JsonResult(int i, String message) {
        this.status = i;
        this.msg = message;
    }

    public static <V> JsonResult ok(V t) {
        return new JsonResult(t);
    }

    public static JsonResult error(String message) {
        return new JsonResult(500, message);
    }
}

