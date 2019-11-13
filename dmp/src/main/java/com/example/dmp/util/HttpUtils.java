package com.example.dmp.util;

import org.springframework.http.HttpHeaders;

/**
 * @className: HttpUtils
 * @description: TODO
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/11/13 11:28
 */
public class HttpUtils {

    public static HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        return headers;
    }
}
