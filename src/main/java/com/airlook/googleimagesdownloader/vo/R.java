package com.airlook.googleimagesdownloader.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * @description: <公共响应类>
 * @author: qius
 * @create: 2019-08-07 10:31
 **/
@Data
@Accessors(chain = true)
@ToString
public class R<T> {
    
    /**
     * 错误码
     */
    private int code;
    
    /**
     * 错误信息
     */
    private String msg;
    
    /**
     * 流水号
     */
    private String requestId;
    
    /**
     * 数据
     */
    private T data;
    
    public R() {
    }
    
    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public static R ok() {
        return new R(200, "success");
    }
}
