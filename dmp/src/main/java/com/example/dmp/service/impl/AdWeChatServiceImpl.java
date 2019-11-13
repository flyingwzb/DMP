package com.example.dmp.service.impl;

import com.example.dmp.exception.ServiceException;
import com.example.dmp.service.AdWeChatService;
import com.example.dmp.util.HttpUtils;
import com.example.dmp.util.JsonLUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: AdWeChatServiceImpl
 * @description: 微信广告数据接入接口
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/11/11 14:45
 */
@Service
@Slf4j
public class AdWeChatServiceImpl implements AdWeChatService {

    // 数据源ID
    private static final Integer USER_ACTION_SET_ID = 1109624852;

    private static final String WECHAT_APP_ID = "QWERTYUIOPASDFGHJKL";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addUserActions(String accessToken,String openId) {
        String url = "https://api.weixin.qq.com/marketing/user_actions/add?version=v1.0&access_token=" + accessToken;
        Map<String, Object> postData = new HashMap<>();
        postData.put("user_action_set_id", USER_ACTION_SET_ID);
        // 发生行为转换的URL
        postData.put("url", "https://res-cust.91datong.com/tf");
        // 当前时间的时间戳，单位秒
        postData.put("action_time", Instant.now().getEpochSecond());
        // COMPLETE_ORDER（下单）、RESERVATION（表单预约）、REGISTER（注册）
        postData.put("action_type", "RESERVATION");

        Map<String, String> userId = new HashMap<>();
        userId.put("wechat_app_id", WECHAT_APP_ID);
        userId.put("wechat_openid", openId);
        postData.put("user_id", userId);

        Map<String, String> actionParam = new HashMap<>();
        // 转化数据发生的渠道，Biz代表公众号，Web代表非公众号的其他渠道
        actionParam.put("source", "Biz");
        // 归因方式，0按点击行为归因，1按关注行为归因
        actionParam.put("claim_type", "1");
        postData.put("action_param", actionParam);

        String req = JsonLUtils.toJSon(postData);
        log.info("json:" + req);
        HttpEntity<String> formEntity = new HttpEntity<>(req, HttpUtils.setHeaders());
        Map<String, Object> result = restTemplate.postForObject(url, formEntity, Map.class);
        log.info("调用接口返回结果: {}", result);
        String errcode = (String) result.get("errcode");
        if ("0".equals(errcode)) {
            log.info("上报用户行为数据成功");
        } else {
            log.error("调用微信传递数据源错误: {}", result);
            throw new ServiceException("调用微信传递数据源错误");
        }
    }
}
