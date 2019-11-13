package com.example.dmp.controller;

import com.example.dmp.bean.ResultBean;
import com.example.dmp.service.AdTuiaService;
import com.example.dmp.vo.TuiaInputVo;
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
 * @className: NoAuthTuiaController
 * @description: 推啊广告投放渠道
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/8/28 15:20
 */
@Slf4j
@Api(tags = "推啊广告投放接口")
@RequestMapping("/noauth/tuia")
@RestController
public class AdTuiaController extends BaseController {

    @Autowired
    private AdTuiaService adTuiaService;

    @ApiOperation(value = "推啊回传用户信息", notes = "推啊回传用户信息")
    @PostMapping()
    public ResultBean<String> authorize(@Valid @RequestBody TuiaInputVo inputVo) {
        adTuiaService.conversionUser(inputVo);
        return this.success();
    }
}
