package com.example.dmp.exception;

/**
 * @ClassName:    DefaultException
 * @Description:  TODO
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 16:40
 * @Version:      V1.0
 * @Since:        V1.0
 */
public class DefaultException extends RuntimeException {

	private static final long serialVersionUID = -8634700792767837033L;

	public int errorCode;

	public DefaultException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public DefaultException(String message) {
		super(message);
	}
}
