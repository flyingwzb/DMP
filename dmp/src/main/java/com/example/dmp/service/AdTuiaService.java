package com.example.dmp.service;

import com.example.dmp.vo.TuiaInputVo;

/**
 * @className: AdTuiaService
 * @description: 推啊广告API
 * @author: wangzb01
 * @version: V1.0
 * @since: V1.0
 * @date: 2019/8/6 9:54
 */
public interface AdTuiaService {

    /**
     * @description 推啊用户转化信息回传
     * @author wangzb01
     * @date 2019/8/6 10:17
     * @param inputVo
     * @return java.lang.String
     */
    void conversionUser(TuiaInputVo inputVo);

}
