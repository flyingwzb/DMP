package com.example.dmp.vo;

import lombok.Data;

/**
 * @ClassName:    DoumobOrderDeepVo
 * @Description:  豆萌后续转换入参
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 17:12
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Data
public class DoumobOrderDeepVo {

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
     * 1 待发货 2 待收货 3 已签
     * 收 4 已退货 5 取消发货
     */
    private Integer status;

    /**
     * 订单产生的的日期 格式
     * 为 yyyyMMdd 例如
     * 20180911
     */
    private String orderDate;
}
