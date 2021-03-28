package com.zfkj.handbook.utils;

public class Result {
    private String statusCode; //状态码

    private String message;  //返回消息

    private Object data;  //返回数据

    public Result(String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Result(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = "";

    }

    public Result(Object data) {
        this.statusCode = "200";
        this.message = "ok";
        this.data = data;
    }

    public static Result success(String statusCode, String message, Object data) {
        return new Result(statusCode, message, data);
    }

    public static Result success(String statusCode, String message) {
        return new Result(statusCode, message);
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result error(String statusCode, String message) {
        return new Result(statusCode, message);
    }


    public static Result error(String statusCode) {
        return new Result(statusCode);
    }


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
