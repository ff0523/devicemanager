package com.zg.devicemanager.common;

import lombok.Data;

/**
 * 全局统一返回结果类
 */
@Data
public class Result<T> {
    private int code;
    private T data;
    private String message;
    private long drow;
    private long iTotalDisplayRecords;

    public Result() {
    }
    public static <T> Result<T> success() {
        return fill(ResultCodeEnum.SUCCESS);
    }
    public static <T> Result<T> success(T data) {
        return fill(data,ResultCodeEnum.SUCCESS);
    }
    public static <T> Result<T> fail() {
        return fill( null, ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fill(T data, ResultCodeEnum codeEnum) {
        return fill(codeEnum.getCode(),data,codeEnum.getMessage());
    }

    public static <T> Result<T> fill(ResultCodeEnum codeEnum) {
        return fill(codeEnum.getCode(),codeEnum.getMessage());
    }

    public static <T> Result<T> fill(int code,T data,String msg) {
        Result R = new Result();
        R.setCode(code);
        R.setData(data);
        R.setMessage(msg);
        return R;
    }

    public static <T> Result<T> fill(int code,String msg) {
        Result R = new Result();
        R.setCode(code);
        R.setMessage(msg);
        return R;
    }
    public static <T> Result<T> success(T data,long drow, long iTotalRecords) {
        return fill(data,ResultCodeEnum.SUCCESS,drow,iTotalRecords);
    }
    public static <T> Result<T> fill(T data, ResultCodeEnum codeEnum,long drow, long iTotalRecords) {
        return fill(codeEnum.getCode(),data,codeEnum.getMessage(),drow,iTotalRecords);
    }
    public static <T> Result<T> fill(int code,T data,String msg,long drow, long iTotalRecords) {
        Result R = new Result();
        R.setCode(code);
        R.setData(data);
        R.setMessage(msg);
        R.setDrow(drow);
        R.setITotalDisplayRecords(iTotalRecords);
        return R;
    }
}