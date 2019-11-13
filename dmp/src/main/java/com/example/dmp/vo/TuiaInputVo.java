package com.example.dmp.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @className: TuiaInputVo
 * @description: 推啊用户转化信息回传入参
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/8/6 10:05
 */
@Data
public class TuiaInputVo {


    @ApiModelProperty(value = "推啊订单ID(从落地页URL中截取)", required = true)
    @NotEmpty(message = "推啊订单ID不能为空")
    private String a_oId;


    /**
     * 推啊广告秘钥(联系 AE 获取)
     */
//    private String advertKey = "F37E22641673A9B7EB293A40E29E05E4";

    /**
     * 1：安装APP
     * 2：启动APP
     * 3：注册账号
     * 4：激活账号
     * 5：登录账号
     * 6：用户付费
     * 7：用户进件
     * 8：用户完件
     * 9：用户签收
     * 10：用户拒签
     */
//    private Integer type = 3;

    /**
     * 用户 IP
     */
    //private String ip;

    /**
     * 系统(iOS,Android)
     */
    //private String os;

    /**
     * 操作系统版本号
     */
    //private String osVersion;

    /**
     * 手机型号
     */
    //private String model;

    /**
     * 用户代理User-Agent
     */
    //private String ua;

    @ApiModelProperty(value = "渠道ID", required = true)
    @NotEmpty(message = "渠道ID不能为空")
    private String channelId;

    /**
     * 手机品牌
     */
    //private String brand;

    /**
     * 扩展字段(JSON格式字符串)
     */
    //private String ex;

    /**
     * 合作key
     */
    //private String partnerKey;

    /**
     * 签名
     */
    //private String signature;

    /**
     * 用户名
     */
    //private String userName;

    /**
     * 性别 1:男；2:女
     */
    //private Integer sex;

    /**
     * 用户手机号
     */
    //private String phone;

    /**
     * 用户年龄
     */
    //private Integer age;

    /**
     * 地区（省市区）
     */
    //private String area;

    /**
     * 机器标识
     */
    //private String device;

}
