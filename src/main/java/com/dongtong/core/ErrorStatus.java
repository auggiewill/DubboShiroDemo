package com.dongtong.core;

public enum ErrorStatus {
	STATUS_1001(1001, "请求转换的日期与转换格式不符"),
	STATUS_1002(1002, "错误的爬虫任务构造,请检查代码"),

    STATUS_1099(1099, "test");

    private ErrorStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
