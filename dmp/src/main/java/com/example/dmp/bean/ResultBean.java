package com.example.dmp.bean;

import com.example.dmp.enums.ResultStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName:    ResultBean
 * @Description:  接口返回结果bean
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 11:43
 * @Version:      V1.0
 * @Since:        V1.0
 */
@ApiModel(value = "接口返回")
@Data
public class ResultBean<T> implements Serializable {

    /**
     * 返回状态码 ResultStatus
     */
    @ApiModelProperty(value = "返回状态码", example = "1")
    private Integer status;

    /**
     * 状态信息
     */
    @ApiModelProperty(value = "状态信息", example = "请求成功")
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public ResultBean(Integer status, String msg, T data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(Integer status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    public ResultBean() {
        super();
    }

    @JsonIgnore
    public boolean isSuccess() {
        if (ResultStatusEnum.SUCCESS.getStatus().equals(status)) {
            return true;
        }
        return false;
    }


}
