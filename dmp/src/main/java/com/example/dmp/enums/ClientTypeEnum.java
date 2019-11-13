package com.example.dmp.enums;

/**
 * 
 * @ClassName ClientType
 * @Description 客户端类型
 * @author houkj
 * @version V1.0
 * @since V1.0
 * @date 2018年6月12日下午2:15:35
 *
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
