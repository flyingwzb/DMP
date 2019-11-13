package com.example.dmp.enums;

/**
 * @ClassName:    ClientTypeEnum
 * @Description:  客户端类型
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 16:40
 * @Version:      V1.0
 * @Since:        V1.0
 */
public enum ClientTypeEnum {
	
	/**
	 * 微信公众号客户端
	 */
	wxmp,
	
	/**
	 * 微信小程序客户端
	 */
	wxss,

	/**
	 * H5客户端
	 */
	h5,

	/**
	 * 快保app
	 */
	app;

	public static String toStr() {
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("(");
		for (ClientTypeEnum client : ClientTypeEnum.values()) {
			strBuff.append(client.name()+",");
		}
		strBuff.append(")");
		return strBuff.toString();
	}
	
	public static boolean isClient(String type) {
		for (ClientTypeEnum client : ClientTypeEnum.values()) {
			if (client.name().equals(type)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否为微信公众号
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isWxmp(String type) {
		if (wxmp.name().equals(type)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为微信小程序
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isWxss(String type) {
		if (wxss.name().equals(type)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为H5
	 *
	 * @param type
	 * @return
	 */
	public static boolean isH5(String type) {
		if (h5.name().equals(type)) {
			return true;
		}
		return false;
	}


}
