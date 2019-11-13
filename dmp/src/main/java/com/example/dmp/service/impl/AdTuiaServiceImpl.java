package com.example.dmp.service.impl;

import com.example.dmp.service.AdTuiaService;
import com.example.dmp.vo.TuiaInputVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: AdTuiaServiceImpl
 * @description: 推啊广告API
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/8/6 10:17
 */
@Slf4j
@Service
public class AdTuiaServiceImpl implements AdTuiaService {

    /**
     * 推啊广告秘钥
     */
    private static final String ADVERT_KEY = "QWERTYUIOPASDFGHJKL";

    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public void conversionUser(TuiaInputVo inputVo) {
        String url = "https://activity.tuia.cn/log/effect/v2";
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("a_oId", inputVo.getA_oId());
        paramMap.put("advertKey", ADVERT_KEY);
        paramMap.put("type", 3);
        paramMap.put("channelId", inputVo.getChannelId());
        Map<String, String> resultMap = restTemplate.postForObject(url, paramMap, Map.class);
        if ("0000000".equals(resultMap.get("record"))) {
            log.info("推啊回传用户信息成功，推啊订单ID为：{}", inputVo.getA_oId());
        } else {
            log.error("推啊回传用户信息失败：{}", resultMap.get("redesc"));
        }
    }
}
