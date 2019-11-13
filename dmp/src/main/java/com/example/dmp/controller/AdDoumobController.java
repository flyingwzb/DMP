package com.example.dmp.controller;

import com.example.dmp.bean.ResultBean;
import com.example.dmp.service.AdDoumobService;
import com.example.dmp.vo.IdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName:    AdDoumobController
 * @Description:  豆盟广告API对接
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 17:10
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Slf4j
@Api(tags = "豆萌转化接口")
@RequestMapping("/noauth/doumob")
@RestController
public class AdDoumobController extends BaseController {

    @Autowired
    private AdDoumobService adDoumobService;

    @ApiOperation(value = "豆萌初步转化", notes = "豆萌初步转化")
    @PostMapping("/deepTranslate")
    public ResultBean<String> deepTranslate(@Valid @RequestBody IdVo idVo) {
        String result = adDoumobService.deepTranslate(idVo.getId());
        return this.success(result);
    }
}
