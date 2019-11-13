package com.example.dmp.vo;

import lombok.Data;

/**
 * @ClassName:    DoumobDeepVo
 * @Description:  豆萌初步转换入参
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 17:12
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Data
public class DoumobDeepVo {

    /**
     * 账户唯一标识，豆盟提供
     */
    private String accountId;

    /**
     * 用户校验码
     */
    private String encryptCode;

    /**
     * 豆盟用户标识
     */
    private String dkey;

    /**
     * 1 转化 2 未转化
     */
    private Integer status;
}
