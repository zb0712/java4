package com.szb.java4.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;

/**
 * 统一结果封装类
 * @author 石致彬
 * @create 2021-02-17 14:14
 */
@Data
@ApiModel("统一返回结果")
public class Result {
    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("状态码，200表示成功，201表示失败")
    private Integer code;
    @ApiModelProperty("错误信息")
    private String msg;
    @ApiModelProperty("数据")
    private HashMap<String,Object> data = new HashMap<>();

    private Result(){}
    public static Result ok(HashMap<String,Object> map) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(map);
        return result;
    }
    public static Result error(String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(201);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
