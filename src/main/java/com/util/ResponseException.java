package com.util;

/**
 * @author xuhongnan
 * 创建时间 2019-7-16
 */
public class ResponseException extends RuntimeException {
    private Object data;

    private ResponseCode code;

    public ResponseException(String message) {
        super(message);
        this.data = null;
        this.code = ResponseCode.ERROR;
    }

    public ResponseException(String message, Object data, ResponseCode code) {
        super(message);
        this.data = data;
        this.code = code;
    }

    public ResponseException(String message, Throwable cause, Object data, ResponseCode code) {
        super(message, cause);
        this.data = data;
        this.code = code;
    }

    public ResponseException(Throwable cause, Object data, ResponseCode code) {
        super(cause);
        this.data = data;
        this.code = code;
    }

    public ResponseException(String message, Throwable cause, Object data,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = ResponseCode.ERROR;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }
}

