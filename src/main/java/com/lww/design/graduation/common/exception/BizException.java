package com.lww.design.graduation.common.exception;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(Exception e) {
        super(e);
    }

    public BizException(int errorCode, String msg) {
        super(msg);
        this.code = errorCode;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}