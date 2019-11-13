package com.example.dmp.service.impl;

import com.example.dmp.exception.ServiceException;
import com.example.dmp.service.AdDoumobService;
import com.example.dmp.util.EmptyUtils;
import com.example.dmp.util.MD532Utils;
import com.example.dmp.vo.DoumobDeepVo;
import com.example.dmp.vo.DoumobOrderDeepVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ClassName:    AdDoumobServiceImpl
 * @Description:  豆盟广告API对接
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 17:11
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Service
@Slf4j
public class AdDoumobServiceImpl implements AdDoumobService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 豆萌密钥
     */
    private final static String SECRET = "7d0a523ba74c8d12";

    @Override
    public String deepTranslate(String dkey) {
        if (EmptyUtils.isEmpty(dkey)) {
            throw new ServiceException("豆盟平台用户操作标识dkey 为空，转化失败");
        }
        DoumobDeepVo doumobDeepVo = new DoumobDeepVo();
        doumobDeepVo.setDkey(dkey);
        //默认转化
        doumobDeepVo.setStatus(1);
        doumobDeepVo.setAccountId("7e3fc5137d0a523ba74c8d12eff0c6f0");
        String encryptCode = MD532Utils.toMd532LowerString(doumobDeepVo.getAccountId() + doumobDeepVo.getDkey() + doumobDeepVo.getStatus() + SECRET);
        doumobDeepVo.setEncryptCode(encryptCode);
        String url = "https://openapi.bayimob.com/openApi/deepTranslate";
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("accountId", doumobDeepVo.getAccountId());
        map.add("encryptCode", doumobDeepVo.getEncryptCode());
        map.add("dkey", doumobDeepVo.getDkey());
        map.add("status", doumobDeepVo.getStatus());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
        //发送请求，设置请求返回数据格式为String
        String result = "";
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);
        if (EmptyUtils.isNotEmpty(responseEntity) && null != responseEntity.getBody()) {
            result = responseEntity.getBody().toString();
        }
        log.info("豆萌初步转换结果：" + result);
        return result;
    }


    @Override
    public String orderDeepTranslate(DoumobOrderDeepVo doumobOrderDeepVo) {
        doumobOrderDeepVo.setAccountId("7e3fc5137d0a523ba74c8d12eff0c6f0");
        String encryptCode = MD532Utils.toMd532LowerString(doumobOrderDeepVo.getAccountId() + doumobOrderDeepVo.getDkey() + doumobOrderDeepVo.getStatus() + SECRET);
        doumobOrderDeepVo.setEncryptCode(encryptCode);
        String url = "https://openapi.bayimob.com/openApi/orderDeepTranslate";
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("accountId", doumobOrderDeepVo.getAccountId());
        map.add("encryptCode", doumobOrderDeepVo.getEncryptCode());
        map.add("dkey", doumobOrderDeepVo.getDkey());
        map.add("status", doumobOrderDeepVo.getStatus());
        map.add("orderDate", doumobOrderDeepVo.getOrderDate());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
        //发送请求，设置请求返回数据格式为String
        String result = "";
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);
        if (EmptyUtils.isNotEmpty(responseEntity) && null != responseEntity.getBody()) {
            result = responseEntity.getBody().toString();
        }
        log.info("豆萌深度转换结果：" + result);
        return result;
    }
}
