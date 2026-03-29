package com.example.studentmanager.model.entity;

public class Result {
    private Integer code;
    private String msg;
    private Object data;

    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    //操作成功 没有数据返回
    public static Result success(String msg) {
        return new Result(1, msg, null);
    }

    //操作成功 有数据返回
    public static Result success(Object data) {
        return new Result(1, "操作成功", data);
    }

    //操作失败
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }

    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}