package com.example.dmp.exception;

/**
 * @ClassName:    ServiceException
 * @Description:  全局异常捕获类
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 16:39
 * @Version:      V1.0
 * @Since:        V1.0
 */
public class ServiceException extends DefaultException {

	private static final long serialVersionUID = -8634700792767837033L;

	//public int errorCode;

	public ServiceException(int errorCode, String message) {
		super(errorCode,message);
		//this.errorCode = errorCode;
	}
	
	public ServiceException(String message) {
		super(message);
	}
}
