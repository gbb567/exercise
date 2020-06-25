package com.gbb.content.exceptions;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class CustomRuntimeException extends RuntimeException {
    private int code;
    public CustomRuntimeException(int code,String message) {
        super(message);
        this.code = code;
    }
    public int getCode() {return code;}
}
