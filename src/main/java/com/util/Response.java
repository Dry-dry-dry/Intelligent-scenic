package com.util;

import lombok.Data;

/**
 * @author xuhongnan
 * 创建时间 2019-7-16
 */
@Data
public class Response<T> {
    private ResponseCode code;
    private String msg;
    private T data;

    public static <T> Response<T> ok(){return ok(null);}

    public static <T> Response<T> ok(T data) {return create(ResponseCode.OK, "操作成功", data);}

    public static <T> Response<T> error(T data){return create(ResponseCode.ERROR, "操作失败" );}

    public static <T> Response<T> create(ResponseCode code, String message, T data) {
        return new Response<T>(code, message, data);
    }

    public static <T> Response<T> create(ResponseCode code, String message) {
        return create(code, message, null);
    }

    public Response(ResponseCode code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }
}
