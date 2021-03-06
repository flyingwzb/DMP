package com.example.dmp.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:    PageMsg
 * @Description:  分页bean
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 11:43
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Data
public class PageMsg {

    /**
     * 页码，从1开始
     */
    @ApiModelProperty(value = "页码，从1开始", example = "1" ,required = true)
    private int pageNum = 1;

    /**
     * 页面大小
     */
    @ApiModelProperty(value = "页面大小", example = "10",required = true)
    private int pageSize = 10;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", example = "name")
    private String sort;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序方式 DESC倒序，ASC正序，不传默认倒序", example = "DESC")
    private String direction;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数", example = "1000")
    private long total;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数", example = "100")
    private int pages;

}
