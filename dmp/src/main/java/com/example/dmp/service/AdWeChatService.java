package com.example.dmp.service;

/**
 * @className: AdWeChatService
 * @description: 微信广告数据接入接口
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/11/11 14:44
 */
public interface AdWeChatService {

    /**
     * @description 微信广告回传数据
     * @author wangzb01
     * @date 2019/11/11 14:58
     * @param openId
     * @return void
     */
    void addUserActions(String accessToken,String openId);
}
