package com.example.dmp.controller;

import com.example.dmp.bean.ResultBean;
import com.example.dmp.enums.TencentAccountTypeEnum;
import com.example.dmp.service.AdTencentService;
import com.example.dmp.vo.IdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @className: NoAuthTencentController
 * @description: 腾讯广告OAuth授权(OAuth 2.0)接口
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/8/15 14:17
 */
@Slf4j
@Api(tags = "腾讯广告授权认证接口")
@RequestMapping("/noauth/tencent")
@RestController
public class AdTencentController extends BaseController {

    @Autowired
    private AdTencentService adTencentService;

    @ApiOperation(value = "获取AuthorizationCode", notes = "获取AuthorizationCode")
    @PostMapping("/authorize")
    public ResultBean<String> authorize(@Valid @RequestBody IdVo idVo) {
        String authorizationCode = adTencentService.getCode(idVo.getId());
        return this.success(authorizationCode);
    }

    @ApiOperation(value = "通过AuthorizationCode获取AccessToken", notes = "通过AuthorizationCode获取AccessToken")
    @GetMapping("/token")
    public ResultBean<String> getToken(@RequestParam("code") String authorizationCode, @RequestParam("type") String type) {
        String accessToken = adTencentService.getTokenByCode(authorizationCode, type);
        return this.success(accessToken);
    }

    @ApiOperation(value = "根据RefreshToken刷新AccessToken", notes = "根据RefreshToken刷新AccessToken")
    @GetMapping("/refresh/token")
    public ResultBean<String> refreshToken(@RequestParam("token") String refreshToken, @RequestParam("type") String type) {
        String accessToken = adTencentService.refreshToken(refreshToken, type);
        return this.success(accessToken);
    }

    @ApiOperation(value = "广点通上传用户行为数据_北京亿科", notes = "广点通上传用户行为数据_北京亿科")
    @GetMapping("/user/action/qq")
    public ResultBean<String> addUserActionForQQ(@RequestParam("phone") String phoneNumber) {
        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.QQ1.name(),"REGISTER");
        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.QQ1.name(),"RESERVATION");
        return this.success();
    }

    @ApiOperation(value = "广点通上传用户行为数据_上海微盟", notes = "广点通上传用户行为数据_上海微盟")
    @GetMapping("/user/action")
    public ResultBean<String> addUserAction(@RequestParam("phone") String phoneNumber) {
        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.QQ2.name(),"REGISTER");
        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.QQ2.name(),"RESERVATION");
        return this.success();
    }

    @ApiOperation(value = "微信上传用户行为数据_大童", notes = "微信上传用户行为数据_大童")
    @GetMapping("/user/action/wx")
    public ResultBean<String> addUserActionForWX(@RequestParam("phone") String phoneNumber) {
        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.WX.name(),"REGISTER");
        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.WX.name(),"RESERVATION");
        return this.success();
    }

//    @ApiOperation(value = "微信上传用户行为数据_北京亿科", notes = "微信上传用户行为数据_北京亿科")
//    @GetMapping("/user/action/wx2")
//    public ResultBean<String> addUserActionForWX2(@RequestParam("phone") String phoneNumber) {
//        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.WX2.name());
//        return this.success();
//    }
//
//    @ApiOperation(value = "微信上传用户行为数据_上海微盟", notes = "微信上传用户行为数据_上海微盟")
//    @GetMapping("/user/action/wx3")
//    public ResultBean<String> addUserActionForWX3(@RequestParam("phone") String phoneNumber) {
//        adTencentService.addUserAction(phoneNumber, TencentAccountTypeEnum.WX3.name());
//        return this.success();
//    }

//    @ApiOperation(value = "用户行为数据报表", notes = "用户行为数据报表")
//    @GetMapping("/user/action/report")
//    public ResultBean<String> userActionSetReports() {
//        adTencentService.userActionSetReports("QQ2");
//        return this.success();
//    }

}

