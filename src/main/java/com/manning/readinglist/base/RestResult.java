package com.manning.readinglist.base;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RestResult {

    public static final Integer OK = 0;
    public static final Integer NULL_POINTER = 10;
    public static final Integer NOT_FOUND = 20;
    public static final Integer READER_NOT_FOUND = 21;
    public static final Integer ERROR = 100;

    public static Map<Integer, String> resultMap = new HashMap<>();
    static {
        resultMap.put(OK, "Success");
        resultMap.put(NULL_POINTER, "Null Value Exist!");
        resultMap.put(NOT_FOUND, "Not Found!");
        resultMap.put(READER_NOT_FOUND, "Reader Not Found!");
        resultMap.put(ERROR, "Service Error!");
    }

    private Integer code;

    private String message;

    private String url;

    private Object data;

    public RestResult() {
    }

    private RestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private RestResult(Integer code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    private RestResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private RestResult(Integer code, String message, String url, Object data) {
        this.code = code;
        this.message = message;
        this.url = url;
        this.data = data;
    }

    @NotNull
    public static RestResult success(String message) {
        return new RestResult(OK, message);
    }

    @NotNull
    public static RestResult success(String message, String url) {
        return new RestResult(OK, message, url);
    }

    @NotNull
    public static RestResult success(String message, Object data) {
        return new RestResult(OK, message, data);
    }

    @NotNull
    public static RestResult success(String message, String url, Object data) {
        return new RestResult(OK, message, url, data);
    }

    @NotNull
    public static RestResult error(Integer code, String message) {
        return new RestResult(code, message);
    }

    @NotNull
    public static RestResult error(Integer code, String message, String url) {
        return new RestResult(code, message, url);
    }

    @NotNull
    public static RestResult error(Integer code, String message, Object data) {
        return new RestResult(code, message, data);
    }

    @NotNull
    public static RestResult error(Integer code, String message, String url, Object data) {
        return new RestResult(code, message, url, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                ", data=" + data +
                '}';
    }
}
