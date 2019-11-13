package com.example.dmp.service;

import com.example.dmp.vo.DoumobOrderDeepVo;

/**
 * @ClassName:    AdDoumobService
 * @Description:  豆盟广告API对接
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 17:12
 * @Version:      V1.0
 * @Since:        V1.0
 */
public interface AdDoumobService {

    /**
     * @Description  豆萌初步转换数据接口
     * @Author       王志彪(Will Wang)
     * @Date         2019/11/13 17:13
     * @Param        [dkey]
     * @Return       java.lang.String
     */
    String deepTranslate(String dkey);

    /**
     * @Description  豆萌后续转换数据接口
     * @Author       王志彪(Will Wang)
     * @Date         2019/11/13 17:14
     * @Param        [doumobOrderDeepVo]
     * @Return       java.lang.String
     */
    String orderDeepTranslate(DoumobOrderDeepVo doumobOrderDeepVo);
}
