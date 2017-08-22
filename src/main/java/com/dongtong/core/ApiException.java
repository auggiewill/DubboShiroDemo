package com.dongtong.core;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
	public static final String DETAIL_MESSAGE_FORMAT = "%s%s";

    private int statusCode;

    private String statusMessage;


    public ApiException(int code, String message) {
        super(String.format(DETAIL_MESSAGE_FORMAT, code, message));
        this.statusCode = code;
        this.statusMessage = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
