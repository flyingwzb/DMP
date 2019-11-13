package com.example.dmp.service.impl;

import com.example.dmp.enums.TencentAccountTypeEnum;
import com.example.dmp.enums.TencentGrantTypeEnum;
import com.example.dmp.exception.ServiceException;
import com.example.dmp.service.AdTencentService;
import com.example.dmp.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @className: AdTencentServiceImpl
 * @description: 腾讯社交广告_网站行为数据接入
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/8/12 11:52
 */
@Service
@Slf4j
public class AdTencentServiceImpl implements AdTencentService {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private RedisTemplate<String, Object> redisJsonTemplate;

    /**
     * 通用参数
     */
    private static final Integer CLIENT_ID = 1109760298;
    private static final String CLIENT_SECRET = "QWERTYUIOP";
    private static final String REDIRECT_URI = "www.google.com";
    private static final String WECHAT_ACCOUNT_ID = "ASDFGHJKL";

    /**
     * 广点通代理商_广告子账户_北京亿科
     */
    private static final Integer ACCOUNT_ID_1 = 10795851;
    private static final Integer USER_ACTION_SET_ID_1 = 1109556843;

    /**
     * 广点通代理商_广告子账户_上海微盟
     */
    private static final Integer ACCOUNT_ID_2 = 11411021;
    private static final Integer USER_ACTION_SET_ID_2 = 1109823784;

    /**
     * 微信广告账户_old
     */
    private static final Integer ACCOUNT_ID_3 = 10656409;
    private static final Integer USER_ACTION_SET_ID_3 = 1109624852;

    /**
     * 代理商账户下的授权行为_不可用
     * 4_old_北京亿科
     */
    private static final Integer ACCOUNT_ID_4 = 10683481;
    private static final Integer USER_ACTION_SET_ID_4 = 1109600189;

    /**
     * 代理商账户下的授权行为_不可用
     * 5_new_上海微盟
     */
    private static final Integer ACCOUNT_ID_5 = 11341385;
    private static final Integer USER_ACTION_SET_ID_5 = 1109811600;

    @Override
    public String getCode(String accountType) {
        String url = "https://developers.e.qq.com/oauth/authorize";
        Map<String, Object> mapParam = new HashMap<>(16);
        // 应用 id
        mapParam.put("client_id", CLIENT_ID);
        // 应用回调地址
        mapParam.put("redirect_uri", REDIRECT_URI);
        // 验证请求有效性参数
        mapParam.put("state", "insure");
        // 授权范围
        mapParam.put("scope", "user_actions");
        // 枚举列表：{ ACCOUNT_TYPE_WECHAT, ACCOUNT_TYPE_QQ }
        mapParam.put("account_type", accountType);
        mapParam.put("account_display_number", 2);
        String result = restTemplate.getForObject(url, String.class, mapParam);
        log.info("获取AuthorizationCode结果：{}", result);
        String authorizationCode = UrlParserUtil.fromURL(result).getParameter("authorization_code");
        log.info("授权AuthorizationCode为：{}", authorizationCode);
        return authorizationCode;
    }

    @Override
    public String getTokenByCode(String authorizationCode, String type) {
        String url = "https://api.e.qq.com/oauth/token?client_id={client_id}&client_secret={client_secret}&grant_type={grant_type}&authorization_code={authorization_code}&redirect_uri={redirect_uri}";
        Map<String, Object> mapParam = new HashMap<>(16);
        // 应用 id
        mapParam.put("client_id", CLIENT_ID);
        // 应用 secret
        mapParam.put("client_secret", CLIENT_SECRET);
        // 请求的类型(authorization_code（授权码方式获取 token）、refresh_token（刷新 token）)
        mapParam.put("grant_type", TencentGrantTypeEnum.authorization_code.name());
        mapParam.put("authorization_code", authorizationCode);
        mapParam.put("redirect_uri", REDIRECT_URI);

        // 请求响应结果
        Map<String, Object> responseMap = restTemplate.getForObject(url, Map.class, mapParam);
        log.info("获取AccessToken结果：{}", responseMap);
        if (responseMap.get("code").equals(0)) {
            Map<String, Object> responseData = (Map<String, Object>) responseMap.get("data");
            String accessToken = (String) responseData.get("access_token");
            log.info("应用AccessToken:{}", accessToken);
            Integer accessTokenExpiresIn = (Integer) responseData.get("access_token_expires_in");
            log.info("access_token过期时间:{}", accessTokenExpiresIn);
            String refreshToken = (String) responseData.get("refresh_token");
            log.info("应用RefreshToken:{}", refreshToken);
            Integer refreshTokenExpiresIn = (Integer) responseData.get("refresh_token_expires_in");
            log.info("refresh_token过期时间:{}", refreshTokenExpiresIn);
            Map<String, Object> authorizerInfoMap = (Map<String, Object>) responseData.get("authorizer_info");
            Integer accountId = (Integer) authorizerInfoMap.get("account_id");
            log.info("绑定的推广帐号id:{}", accountId);

            String accessTokenRedisKey = RedisKey.setKey("tencent", type, "userActions", "accessToken");
            String refreshTokenRedisKey = RedisKey.setKey("tencent", type, "userActions", "refreshToken");
            //添加accessToken到缓存中
            redisJsonTemplate.opsForValue().set(accessTokenRedisKey, accessToken, accessTokenExpiresIn, TimeUnit.SECONDS);
            //添加refreshToken到缓存中
            redisJsonTemplate.opsForValue().set(refreshTokenRedisKey, refreshToken, refreshTokenExpiresIn, TimeUnit.SECONDS);

            return accessToken;
        } else {
            throw new ServiceException((String) responseMap.get("message"));
        }
    }

    @Override
    public String refreshToken(String refreshToken, String type) {
        String url = "https://api.e.qq.com/oauth/token?client_id={client_id}&client_secret={client_secret}&grant_type={grant_type}&refresh_token={refresh_token}";
        Map<String, Object> mapParam = new HashMap<>(16);
        // 应用 id
        mapParam.put("client_id", CLIENT_ID);
        // 应用 secret
        mapParam.put("client_secret", CLIENT_SECRET);
        // 请求的类型(authorization_code（授权码方式获取 token）、refresh_token（刷新 token）)
        mapParam.put("grant_type", TencentGrantTypeEnum.refresh_token.name());
        mapParam.put("refresh_token", refreshToken);

        // 请求响应结果
        Map<String, Object> responseMap = restTemplate.getForObject(url, Map.class, mapParam);
        log.info("刷新AccessToken结果：{}", responseMap);
        if (responseMap.get("code").equals(0)) {
            Map<String, Object> responseData = (Map<String, Object>) responseMap.get("data");
            String accessToken = (String) responseData.get("access_token");
            log.info("应用AccessToken:{}", accessToken);
            Integer accessTokenExpiresIn = (Integer) responseData.get("access_token_expires_in");
            log.info("access_token过期时间:{}", accessTokenExpiresIn);

            // 添加accessToken到缓存中
            String accessTokenRedisKey = RedisKey.setKey("tencent", type, "userActions", "accessToken");
            redisJsonTemplate.opsForValue().set(accessTokenRedisKey, accessToken, accessTokenExpiresIn, TimeUnit.SECONDS);
            // 刷新refreshToken失效时间
            String refreshTokenRedisKey = RedisKey.setKey("tencent", type, "userActions", "refreshToken");
            redisJsonTemplate.opsForValue().set(refreshTokenRedisKey, refreshToken, 2592000, TimeUnit.SECONDS);
            return accessToken;
        } else {
            throw new ServiceException((String) responseMap.get("message"));
        }
    }


    @Override
    public String getAccessTokenFromRedis(String type) {
        //缓存中能获取accessToken
        String accessTokenRedisKey = RedisKey.setKey("tencent", type, "userActions", "accessToken");
        String accessToken = (String) redisJsonTemplate.opsForValue().get(accessTokenRedisKey);
        if (EmptyUtils.isNotEmpty(accessToken)) {
            return accessToken;
        } else {
            //缓存中能获取accessToken
            String refreshTokenRedisKey = RedisKey.setKey("tencent", type, "userActions", "refreshToken");
            String refreshToken = (String) redisJsonTemplate.opsForValue().get(refreshTokenRedisKey);
            if (EmptyUtils.isNotEmpty(refreshToken)) {
                accessToken = refreshToken(refreshToken, type);
                return accessToken;
            } else {
                throw new ServiceException("腾讯广告授权认证token失效，请重新授权");
            }
        }
    }

    @Override
    public Integer createUserAction(String type) {
        String url = "https://api.e.qq.com/v1.1/user_action_sets/add";
        Map<String, Object> mapParam = new HashMap<>(16);
        mapParam.put("access_token", getAccessTokenFromRedis(type));
        mapParam.put("timestamp", StringLUtils.getCurrentTimestamp());
        mapParam.put("nonce", StringLUtils.generateNonceStr());
        // 推广帐号id，有操作权限的帐号 id，包括代理商和广告主帐号 id
        mapParam.put("account_id", ACCOUNT_ID_1);
        // 用户行为源类型，枚举列表：{ WEB, ANDROID, IOS, OFFLINE }
        mapParam.put("type", "WEB");
        //
        mapParam.put("mobile_app_id", 2222);
        //
        mapParam.put("name", "REGISTER");
        // 用户行为源描述
        mapParam.put("description", "H5注册");
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "application/json");
//        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=utf-8");
        HttpEntity<Map> formEntity = new HttpEntity<>(mapParam, headers);

        Map<String, Object> responseMap = restTemplate.postForObject(url, formEntity, Map.class);
        Map<String, Integer> dataMap = (Map<String, Integer>) responseMap.get("data");
        Integer userActionSetId = dataMap.get("user_action_set_id");
        return userActionSetId;
    }

    @Override
    public Map<String, Object> getUserAction(Integer userActionSetId) {
        String url = "https://api.e.qq.com/v1.1/user_action_sets/get";
        Map<String, Object> mapParam = new HashMap<>(16);
        mapParam.put("access_token", "46788eb9141c2a0300e5a939498cde87");
        mapParam.put("timestamp", StringLUtils.getCurrentTimestamp());
        mapParam.put("nonce", StringLUtils.generateNonceStr());
        mapParam.put("account_id", ACCOUNT_ID_1);
        mapParam.put("user_action_set_id", userActionSetId);
        Map<String, Object> map = restTemplate.getForObject(url, Map.class, mapParam);
        return map;
    }

    @Override
    public void addUserAction(String phoneNumber, String type, String actionType) {
        Integer accountId = null;
        Integer userActionSetId = null;
        if (TencentAccountTypeEnum.QQ1.name().equals(type)) {
            accountId = ACCOUNT_ID_1;
            userActionSetId = USER_ACTION_SET_ID_1;
        } else if (TencentAccountTypeEnum.QQ2.name().equals(type)) {
            accountId = ACCOUNT_ID_2;
            userActionSetId = USER_ACTION_SET_ID_2;
        } else if (TencentAccountTypeEnum.WX.name().equals(type)) {
            accountId = ACCOUNT_ID_3;
            userActionSetId = USER_ACTION_SET_ID_3;
        } else if (TencentAccountTypeEnum.WX2.name().equals(type)) {
            type = TencentAccountTypeEnum.WX.name();
            accountId = ACCOUNT_ID_4;
            userActionSetId = USER_ACTION_SET_ID_4;
        } else if (TencentAccountTypeEnum.WX3.name().equals(type)) {
            type = TencentAccountTypeEnum.WX.name();
            accountId = ACCOUNT_ID_5;
            userActionSetId = USER_ACTION_SET_ID_5;
        }
        String url = "https://api.e.qq.com/v1.1/user_actions/add?access_token=ACCESS_TOKEN&timestamp=TIMESTAMP&nonce=NONCE";
        url = url.replace("ACCESS_TOKEN", getAccessTokenFromRedis(type)).replace("TIMESTAMP", StringLUtils.getCurrentTimestamp()).replace("NONCE", StringLUtils.generateNonceStr());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=utf-8");
        String paramJson = "{\n" +
                "    \"account_id\": \"" + accountId + "\",\n" +
                "    \"user_action_set_id\": \"" + userActionSetId + "\",\n" +
                "    \"actions\": [\n" +
                "        {\n" +
                "            \"action_time\": " + StringLUtils.getCurrentTimestamp() + ",\n" +
                "            \"user_id\": {\n" +
                "                \"hash_phone\": \"" + MD5Utils.MD5(phoneNumber) + "\"\n" +
                "            },\n" +
                "            \"action_type\": \""+actionType+"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        HttpEntity<String> formEntity = new HttpEntity<>(paramJson, headers);
        Map<String, Object> resultMap = restTemplate.postForObject(url, formEntity, Map.class);
        if (resultMap.get("code").equals(0)) {
            log.info(type + "上传用户行为"+actionType+"成功，用户手机号：{}", phoneNumber);
        } else {
            log.error("上传用户行为"+actionType+"失败：{}", resultMap.get("message"));
        }
    }

    @Override
    public void wechatBind(String type) {
        String url = "https://developers.e.qq.com/authorization/wechat_bind";
        Map<String, Object> mapParam = new HashMap<>(16);
        mapParam.put("access_token", getAccessTokenFromRedis(type));
        mapParam.put("redirect_uri", REDIRECT_URI);
        // 需绑定公众号的广告主id
        mapParam.put("account_id", 1109624852);
        // 微信公众号id
        mapParam.put("wechat_account_id", WECHAT_ACCOUNT_ID);
        String result = restTemplate.getForObject(url, String.class, mapParam);
        log.info("绑定微信公众号结果：{}", result);
    }

    @Override
    public void userActionSetReports(String type) {
        String url = "https://api.e.qq.com/v1.1/user_action_set_reports/get?access_token=<ACCESS_TOKEN>&timestamp=<TIMESTAMP>&nonce=<NONCE>";
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("access_token", getAccessTokenFromRedis(type));
        paramMap.put("timestamp", StringLUtils.getCurrentTimestamp());
        paramMap.put("nonce", StringLUtils.generateNonceStr());
        paramMap.put("account_id", ACCOUNT_ID_2);
        paramMap.put("user_action_set_id", USER_ACTION_SET_ID_2);
        Map<String, String> dateRange = new HashMap<>(16);
        dateRange.put("start_date", "2019-08-15");
        dateRange.put("end_date", "2019-08-26");
        paramMap.put("date_range", dateRange);
        paramMap.put("time_granularity", "DAILY");
        Map<String, Object> resultMap = restTemplate.getForObject(url, Map.class, paramMap);
        System.out.println(resultMap);
    }

}
