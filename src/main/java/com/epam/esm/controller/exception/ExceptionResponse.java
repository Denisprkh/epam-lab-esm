package com.epam.esm.controller.exception;

public class ExceptionResponse {

    private int code;
    private String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(String errorMessage) {
        this.message = errorMessage;
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
