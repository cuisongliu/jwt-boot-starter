package com.cuisongliu.jwt.dto;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
public class ErrorResponse {
    private int code;
    private String message;
    public ErrorResponse(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public ErrorResponse(String message) {
        this(500,message);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
