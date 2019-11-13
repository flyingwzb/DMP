package com.example.dmp.enums;

/**
 * @ClassName:    ResultStatusEnum
 * @Description:  api接口返回状态字典
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 10:41
 * @Version:      V1.0
 * @Since:        V1.0
 */
public enum ResultStatusEnum {

	/**
	 *  请求失败
	 */
	FAIL(0,"请求失败"),

	/**
	 *  请求成功
	 */
	SUCCESS(1,"请求成功"),

	/**
	 *  请求超时
	 */
	TIMEOUT(2,"请求超时"),

	/**
	 *  未绑定
	 */
	NOTBIND(3,"未绑定"),

	/**
	 *  用户已存在
	 */
	IS_USER(6,"用户已存在"),

	/**
	 *  认证失败
	 */
	UNAUTHORIZED(401,"认证失败"),

	/**
	 *  访问未授权
	 */
	FORBIDDEN(403, "访问未授权"),

	/**
	 *  找不到地址
	 */
	NOT_FOUND(404, "找不到地址"),

	/**
	 *  请求次数太多
	 */
	TOO_MANY_REQUESTS(429, "请求次数太多"),

	/**
	 *  系统错误
	 */
	NTERNAL_SERVER_ERROR(500, "系统错误");

	/**
	 * 状态码
	 */
	private final Integer status;
	
	/**
	 * 状态说明信息
	 */
	private final String msg;

	ResultStatusEnum(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public static String toStr() {
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("常用状态码：");
		for (ResultStatusEnum client : ResultStatusEnum.values()) {
			strBuff.append(client.getStatus()+":" + client.getMsg() + "，");
		}
		return strBuff.toString();
	}

	public static boolean isSuccess(Integer status){
		if(ResultStatusEnum.SUCCESS.getStatus().equals(status)){
			return true;
		}else{
			return false;
		}
	}
	
	public Integer getStatus(){ return status;};
	
	public String getMsg(){ return msg;};

}
