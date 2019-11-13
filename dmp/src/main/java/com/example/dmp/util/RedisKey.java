package com.example.dmp.util;

import com.example.dmp.enums.ClientTypeEnum;

/**
 * 
 * @ClassName RedisKey
 * @Description redis缓存KEY 定义
 * @author houkj
 * @version V1.0
 * @since V1.0
 * @date 2018年7月19日下午6:35:18
 *
 */
public class RedisKey {

	/**
	 * 所有INP项目的数据存redis时，必须带INP前缀
	 */
	private static final String Header = "RES";
	
	/**
	 * redis key 各字段分隔符
	 */
	public static final String Seg = ":";

	/**
	 * 设置 redis key
	 * @param subKey
	 * @return
	 */
	public static String setKey(String ... subKey ) {
		
		String key = Header + Seg;
		
		for (String str : subKey) {
			key+=( str + Seg );
		}
		
		return key.substring(0, key.length()-1);
	}



	/**
	 * 设置临时refresh存储key
	 * @param refreshToken
	 * @return
	 */
	public static String setTempRefreshTokenKey(String refreshToken) {

		return RedisKey.setKey("temp","refreshToken",refreshToken);

	}



    /**
     * 设置临时计数key
     * @param token
     * @return
     */
    public static String getTempKey(String token){
        return RedisKey.setKey("temp","num",token);
    }

	/**
	 * 微信公众号用户信息key
	 * @param openId
	 * @return
	 */
	public static String setCustWxInfoKey(String openId) {
		return RedisKey.setKey(ClientTypeEnum.wxmp.name(),"custWxInfo",openId);
	}

	/**
	 * 微信小程序用户信息key
	 * @param openId
	 * @return
	 */
	public static String setWxssInfoKey(String openId) {
		return RedisKey.setKey(ClientTypeEnum.wxss.name(),"wxssInfo",openId);
	}

	/**
	 * @description 设置微信小程序AccessToken key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param accessToken
	 * @return java.lang.String
	 */
	public static String setWxSsAccessTokenKey(String accessToken ) {
		return RedisKey.setKey(ClientTypeEnum.wxss.name(),"accessToken",accessToken);
	}

	/**
	 * @description 设置微信公众号认证信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param unionId
	 * @return java.lang.String
	 */
	public static String setWxMpTokenKey(String unionId) {
		return RedisKey.setKey(ClientTypeEnum.wxmp.name(),"unionId",unionId);
	}

	/**
	 * @description 设置H5认证信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param custId
	 * @return java.lang.String
	 */
	public static String setH5TokenKey(String custId) {
		return RedisKey.setKey(ClientTypeEnum.h5.name(),"custId",custId);
	}

	/**
	 * @description 设置微信公众号refreshToken信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param refreshToken
	 * @return java.lang.String
	 */
	public static String setWxMpRefreshTokenKey(String refreshToken) {
		return RedisKey.setKey(ClientTypeEnum.wxmp.name(),"refreshToken",refreshToken);
	}

	/**
	 * @description 设置H5 refreshToken信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param refreshToken
	 * @return java.lang.String
	 */
	public static String setH5RefreshTokenKey(String refreshToken) {
		return RedisKey.setKey(ClientTypeEnum.h5.name(),"refreshToken",refreshToken);
	}

	/**
	 * @description 设置微信公众号accessToken信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param accessToken
	 * @return java.lang.String
	 */
	public static String setWxMpAccessTokenKey(String accessToken) {
		return RedisKey.setKey(ClientTypeEnum.wxmp.name(),"accessToken",accessToken);
	}

	/**
	 * @description 设置H5 accessToken信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param accessToken
	 * @return java.lang.String
	 */
	public static String setH5AccessTokenKey(String accessToken) {
		return RedisKey.setKey(ClientTypeEnum.h5.name(),"accessToken",accessToken);
	}

	/**
	 * @description 设置后台用户权限信息key
	 * @author houkj
	 * @date 2019/4/1 18:05
	 * @param userId
	 * @return java.lang.String
	 */
    public static String setAdminUserMsgKey(String userId) {
		return RedisKey.setKey("admin","usermsg",userId);
    }

	/**
	 * @description 设置微信小程序认证信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param openId
	 * @return java.lang.String
	 */
    public static String setWxSsTokenKey(String openId) {

		return RedisKey.setKey(ClientTypeEnum.wxss.name(),"openId",openId);

    }

	/**
	 * @description 设置微信小程序refreshToken信息 key
	 * @author houkj
	 * @date 2019/3/28 14:04
	 * @param refreshToken
	 * @return java.lang.String
	 */
	public static String setWxSsRefreshTokenKey(String refreshToken) {
		return RedisKey.setKey(ClientTypeEnum.wxss.name(),"refreshToken",refreshToken);
	}

	/**
	 *
	 * @Description 设置后台用户认证令牌Key
	 * @since V1.0
	 * @param accessToken
	 * @return
	 */
	public static String setAdminTokenKey(String accessToken ) {
		return RedisKey.setKey("admin","accessToken",accessToken);
	}
}
