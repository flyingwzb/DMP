package com.example.dmp.controller;

import com.example.dmp.bean.ResultBean;
import com.example.dmp.service.AdWeChatService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: AdWeChatController
 * @description: 微信广告数据接入API
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/11/13 17:19
 */
public class AdWeChatController extends BaseController {

    @Autowired
    private AdWeChatService adWeChatService;

    @ApiOperation(value = "微信回传用户行为数据", notes = "微信回传用户行为数据")
    @GetMapping("/user/action")
    public ResultBean<String> addUserAction() {
        adWeChatService.addUserActions("","","");
        return this.success();
    }
}
