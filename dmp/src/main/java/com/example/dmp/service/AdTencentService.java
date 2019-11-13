package com.example.dmp.service;

import java.util.Map;

/**
 * @className: AdTencentService
 * @description: 腾讯社交广告_网站行为数据接入
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/8/12 11:52
 */
public interface AdTencentService {

    /**
     * @description 获取AuthorizationCode
     * @author wangzb01
     * @date 2019/8/12 18:44
     * @param 
     * @return java.lang.String
     */
    String getCode(String accountType);

    /**
     * @description 通过AuthorizationCode获取AccessToken
     * @author wangzb01
     * @date 2019/8/12 18:44
     * @param 
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    String getTokenByCode(String authorizationCode, String type);

    /**
     * @description 刷新AccessToken
     * @author wangzb01
     * @date 2019/8/19 14:58
     * @param refreshToken
     * @return java.lang.String
     */
    String refreshToken(String refreshToken, String type);

    /**
     * @description 从缓存取AccessToken
     * @author wangzb01
     * @date 2019/8/15 15:14
     * @param
     * @return java.lang.String
     */
    String getAccessTokenFromRedis(String type);

    /**
     * @description 创建用户行为数据源
     * @author wangzb01
     * @date 2019/8/12 14:06
     * @param
     * @return java.lang.String
     */
    Integer createUserAction(String type);

    /**
     * @description 获取用户行为数据源
     * @author wangzb01
     * @date 2019/8/12 14:03
     * @param 
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, Object> getUserAction(Integer userActionSetId);


    /**
     * @description 上传用户行为数据
     * @author wangzb01
     * @date 2019/8/12 14:19
     * @param 
     * @return void
     */
    void addUserAction(String phoneNumber, String type, String actionType);

    /**
     * @description 绑定微信公众号
     * @author wangzb01
     * @date 2019/8/15 13:52
     * @param
     * @return void
     */
    void wechatBind(String type);

    /**
     * @description TODO 
     * @author wangzb01
     * @date 2019/8/27 15:03
     * @param 
     * @return void
     */
    void userActionSetReports(String type);
}
