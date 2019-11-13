package com.example.dmp.controller;

import com.example.dmp.bean.ResultBean;
import com.example.dmp.service.AdBaiduService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: NoAuthBaiduController
 * @description: 百度广告数据接入接口
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/11/11 17:04
 */
@Slf4j
@Api(tags = "百度广告数据接入接口")
@RequestMapping("/noauth/baidu")
@RestController
public class AdBaiduController extends BaseController{

    @Autowired
    private AdBaiduService adBaiduService;

    @ApiOperation(value = "百度广告-用户上传数据", notes = "百度广告-用户上传数据")
    @GetMapping("/convert/data")
    public ResultBean<String> uploadConvertData(@RequestParam("url") String url) {
        adBaiduService.uploadConvertData(url);
        return this.success();
    }
}
