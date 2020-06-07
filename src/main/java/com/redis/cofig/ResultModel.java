package com.redis.cofig;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liucan
 * @Date: 2020/3/2 16:39
 */
@Data
public class ResultModel<T> implements Serializable {


    private static final long serialVersionUID = -3985957493933975343L;

    private Integer code;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    /**
     * 成功 不返回数据直接返回成功信息
     * @return
     */
    public static <T> ResultModel<T> success() {
        ResultModel result = new ResultModel();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功 并且加上返回数据
     * @param data
     * @return
     */
    public static <T> ResultModel<T> success(T data) {
        ResultModel result = new ResultModel();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 成功 自定义成功返回状态 加上数据
     * @param resultCode
     * @param data
     * @return
     */
    public static <T> ResultModel<T> success(ResultCode resultCode, T data) {
        ResultModel result = new ResultModel();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * 单返回失败的状态码
     * @param resultCode
     * @return
     */
    public static <T> ResultModel<T> failure(ResultCode resultCode) {
        ResultModel result = new ResultModel();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回失败的状态码 及 数据
     * @param resultCode
     * @param data
     * @return
     */
    public static <T> ResultModel<T> failure(ResultCode resultCode, T data) {
        ResultModel result = new ResultModel();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
