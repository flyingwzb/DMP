package com.example.dmp.service.impl;

import com.example.dmp.service.AdBaiduService;
import com.example.dmp.util.JsonLUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: AdBaiduServiceImpl
 * @description: 百度广告主回传转化数据接口
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/11/11 15:53
 */
@Service
@Slf4j
public class AdBaiduServiceImpl implements AdBaiduService {

    private static final String TOKEN = "QWERTYUIOP";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void uploadConvertData(String logidUrl) {
        String url = "https://ocpc.baidu.com/ocpcapi/api/uploadConvertData";
        Map<String, Object> postData = new HashMap<>();
        postData.put("token", TOKEN);

        Map<String, Object> conversionTypes = new HashMap<>();
        conversionTypes.put("logidUrl", logidUrl);
        conversionTypes.put("newType", 3);
        postData.put("conversionTypes", conversionTypes);
        String req = JsonLUtils.toJSon(postData);
        log.info("json:" + req);
        HttpEntity<String> formEntity = new HttpEntity<>(req, this.setHeaders());
        Map<String, Object> resultMap = restTemplate.postForObject(url, formEntity, Map.class);
        log.info("请求结果：{}", resultMap);
        Map<String, Object> header = (Map<String, Object>) resultMap.get("header");
        Integer status = (Integer) header.get("status");
        if (status == 0) {
            log.info("百度广告-用户上传数据成功");
        } else {
            log.error("百度广告-用户上传数据成功:{}",header.get("failures"));
        }
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        return headers;
    }
}
