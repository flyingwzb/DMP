package com.example.dmp.exception;

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
